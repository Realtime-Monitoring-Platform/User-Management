package com.realtime_monitoring.usermanag.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realtime_monitoring.usermanag.dto.role.RoleRequest;
import com.realtime_monitoring.usermanag.dto.role.RoleResponse;
import com.realtime_monitoring.usermanag.kafka.RoleProducer;
import com.realtime_monitoring.usermanag.mapper.RoleMapper;
import com.realtime_monitoring.usermanag.mapper.UserMapper;
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
        Role newrole=roleMapper.toEntity(roleRequest);
        System.out.println("New role before saving:///////////////////////////// " + newrole);
        Role role = this.roleRepository.save(newrole);
        roleProducer.sendRoleCreation(role);
        System.out.println("Role created:///////////////////////////// " + role);
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse updateRole(UUID roleId, RoleRequest roleRequest) {
        System.out.println("Updating role:///////////////////////////// " + roleRequest.getName());
        Role role = this.roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
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

}
