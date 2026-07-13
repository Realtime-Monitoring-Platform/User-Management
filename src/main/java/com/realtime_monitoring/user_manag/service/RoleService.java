package com.realtime_monitoring.user_manag.service;

import com.realtime_monitoring.user_manag.dto.role.RoleRequest;
import com.realtime_monitoring.user_manag.dto.role.RoleResponse;

public interface RoleService {
    RoleResponse getAllRoles();
    RoleResponse createRole(RoleRequest roleRequest);
    RoleResponse updateRole(Long roleId, RoleRequest roleRequest);
    void deleteRole(Long roleId);

    
}
