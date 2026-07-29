package com.realtime_monitoring.user_manag.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realtime_monitoring.user_manag.dto.role.RoleRequest;
import com.realtime_monitoring.user_manag.dto.role.RoleResponse;
import com.realtime_monitoring.user_manag.kafka.RoleProducer;
import com.realtime_monitoring.user_manag.mapper.RoleMapper;
import com.realtime_monitoring.user_manag.mapper.UserMapper;
import com.realtime_monitoring.user_manag.model.Role;
import com.realtime_monitoring.user_manag.repository.PermissionRepository;
import com.realtime_monitoring.user_manag.repository.RoleRepository;
import com.realtime_monitoring.user_manag.service.RoleService;

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
        Role role = this.roleRepository.save(roleMapper.toEntity(roleRequest));
        roleProducer.sendRoleCreation(role);
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse updateRole(UUID roleId, RoleRequest roleRequest) {
        Role role = this.roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        role = this.roleRepository.save(role);
        roleProducer.sendRoleUpdate(role);
        return roleMapper.toResponse(role);
    }

    @Override
    public void deleteRole(UUID roleId) {
        Role role = this.roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        this.roleRepository.deleteById(roleId);
        roleProducer.sendRoleDeleted(roleId);
    }

}
