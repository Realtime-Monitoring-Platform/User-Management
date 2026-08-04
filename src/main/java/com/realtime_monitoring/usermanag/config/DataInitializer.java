package com.realtime_monitoring.usermanag.config;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.realtime_monitoring.usermanag.config.PermissionDefinition.PermissionDef;
import com.realtime_monitoring.usermanag.config.RoleDefinition.RoleDef;
import com.realtime_monitoring.usermanag.kafka.PermissionProducer;
import com.realtime_monitoring.usermanag.kafka.RoleProducer;
import com.realtime_monitoring.usermanag.model.Permission;
import com.realtime_monitoring.usermanag.model.Role;
import com.realtime_monitoring.usermanag.repository.PermissionRepository;
import com.realtime_monitoring.usermanag.repository.RoleRepository;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionProducer permissionProducer;
    private final RoleProducer roleProducer;

    @Override
    public void run(String... args) {
        seedPermissions();
        seedRoles();
    }

    private void seedPermissions() {
        log.info("Checking for missing permissions to seed…");

        int created = 0;
        int skipped = 0;

        for (PermissionDef def : PermissionDefinition.all()) {
            if (permissionRepository.findByName(def.name()).isPresent()) {
                skipped++;
                continue;
            }

            Permission permission = new Permission();
            permission.setName(def.name());
            permission.setDescription(def.description());
            permission.setResource(def.resource());
            permission.setAction(def.action());

            permissionRepository.save(permission);
            permissionProducer.sendPermissionCreation(permission);
            created++;
        }

        if (created > 0) {
            log.info("Seeded {} new permission(s) into the database.", created);
        } else {
            log.info("All {} permission(s) already exist. Nothing to seed.", skipped);
        }
    }

    private void seedRoles() {
        log.info("Checking for missing roles to seed…");

        int created = 0;
        int skipped = 0;

        for (RoleDef def : RoleDefinition.all()) {
            if (roleRepository.findByName(def.name()).isPresent()) {
                skipped++;
                continue;
            }

            Role role = new Role();
            role.setName(def.name());
            role.setDescription(def.description());
            role.setPermissions(resolvePermissions(def.permissionNames()));

            roleRepository.save(role);
            roleProducer.sendRoleCreation(role);
            created++;
        }

        if (created > 0) {
            log.info("Seeded {} new role(s) into the database.", created);
        } else {
            log.info("All {} role(s) already exist. Nothing to seed.", skipped);
        }
    }

    private Set<Permission> resolvePermissions(Set<String> permissionNames) {
        Set<Permission> permissions = new HashSet<>();
        for (String name : permissionNames) {
            permissionRepository.findByName(name).ifPresent(permissions::add);
        }
        return permissions;
    }
}