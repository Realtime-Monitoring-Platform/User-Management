package com.realtime_monitoring.usermanag.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.realtime_monitoring.usermanag.config.PermissionDefinition.PermissionDef;
import com.realtime_monitoring.usermanag.model.Permission;
import com.realtime_monitoring.usermanag.repository.PermissionRepository;

import lombok.RequiredArgsConstructor;

/**
 * Seeds the database with the canonical set of permissions defined in
 * {@link PermissionDefinition} on every application startup.
 * <p>
 * This ensures that all known permissions always exist in the database
 * without requiring manual migration scripts. Already-existing permissions
 * (matched by name) are skipped.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final PermissionRepository permissionRepository;

    @Override
    public void run(String... args) {
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
            created++;
        }

        if (created > 0) {
            log.info("Seeded {} new permission(s) into the database.", created);
        } else {
            log.info("All {} permission(s) already exist. Nothing to seed.", skipped);
        }
    }
}