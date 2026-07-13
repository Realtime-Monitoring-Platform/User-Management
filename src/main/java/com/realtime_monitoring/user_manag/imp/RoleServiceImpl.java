package com.realtime_monitoring.user_manag.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.realtime_monitoring.user_manag.dto.role.RoleRequest;
import com.realtime_monitoring.user_manag.dto.role.RoleResponse;
import com.realtime_monitoring.user_manag.mapper.RoleMapper;
import com.realtime_monitoring.user_manag.mapper.UserMapper;
import com.realtime_monitoring.user_manag.model.Role;
import com.realtime_monitoring.user_manag.repository.RoleRepository;
import com.realtime_monitoring.user_manag.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleResponse> getAllRoles() {
        return this.roleRepository.findAll().stream().map(roleMapper::toResponse).toList();
    }

    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {
        Role role = this.roleRepository.save(roleMapper.toEntity(roleRequest));
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse updateRole(UUID roleId, RoleRequest roleRequest) {
        Role role = this.roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        role = this.roleRepository.save(role);
        return roleMapper.toResponse(role);
    }

    @Override
    public void deleteRole(UUID roleId) {
        this.roleRepository.deleteById(roleId);
        ;

    }

}
