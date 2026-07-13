package com.realtime_monitoring.user_manag.service;

import java.util.List;
import java.util.UUID;

import com.realtime_monitoring.user_manag.dto.role.RoleRequest;
import com.realtime_monitoring.user_manag.dto.role.RoleResponse;

public interface RoleService {
    List<RoleResponse> getAllRoles();
    RoleResponse createRole(RoleRequest roleRequest);
    RoleResponse updateRole(UUID roleId, RoleRequest roleRequest);
    void deleteRole(UUID roleId);

    
}
