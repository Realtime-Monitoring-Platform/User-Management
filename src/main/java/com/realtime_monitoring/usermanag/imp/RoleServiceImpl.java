package com.realtime_monitoring.usermanag.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realtime_monitoring.usermanag.dto.role.RoleRequest;
import com.realtime_monitoring.usermanag.dto.role.RoleResponse;
import com.realtime_monitoring.usermanag.kafka.RoleProducer;
import com.realtime_monitoring.usermanag.mapper.RoleMapper;
import com.realtime_monitoring.usermanag.mapper.UserMapper;
import com.realtime_monitoring.usermanag.model.Permission;
import com.realtime_monitoring.usermanag.model.Role;
import com.realtime_monitoring.usermanag.repository.PermissionRepository;
import com.realtime_monitoring.usermanag.repository.RoleRepository;
import com.realtime_monitoring.usermanag.service.RoleService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final RoleProducer roleProducer;
    private final PermissionRepository permissionRepository;

    @Override
    public Page<RoleResponse> getAllRoles(Pageable pageable) {
        return this.roleRepository.findAll(pageable).map(roleMapper::toResponse);
    }

    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {
        System.out.println("Creating role:///////////////////////////// " + roleRequest);
        Role newrole = roleMapper.toEntity(roleRequest);

        Set<Permission> permissions = new HashSet<>(
                permissionRepository.findAllById(
                        roleRequest.getPermissionIds()));

        newrole.setPermissions(permissions);

        System.out.println("New role before saving:///////////////////////////// " + newrole);
        Role role = this.roleRepository.save(newrole);
        roleProducer.sendRoleCreation(role);
        System.out.println("Role created:///////////////ro///////////// " + role);
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse updateRole(UUID roleId, RoleRequest roleRequest) {
        System.out.println("Updating role:///////////////////////////// " + roleRequest.getName());
        Role role = this.roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());

        if (roleRequest.getPermissionIds() != null) {
            Set<Permission> permissions = new HashSet<>(
                    permissionRepository.findAllById(
                            roleRequest.getPermissionIds()));
            role.setPermissions(permissions);
        }

        role = this.roleRepository.save(role);
        roleProducer.sendRoleUpdate(role);
        System.out.println("Role updated:///////////////////////////// " + role.getName());
        return roleMapper.toResponse(role);
    }

    @Override
    public void deleteRole(UUID roleId) {
        Role role = this.roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        this.roleRepository.deleteById(roleId);
        roleProducer.sendRoleDeleted(roleId);
    }

    @Override
    public RoleResponse assignPermissionsToRole(UUID roleId, Set<UUID> permissionIds) {
        Role role = this.roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Set<Permission> permissions = new HashSet<>(
                permissionRepository.findAllById(permissionIds));

        role.setPermissions(permissions);
        role = this.roleRepository.save(role);
        roleProducer.sendRoleUpdate(role);
        return roleMapper.toResponse(role);
    }

}
