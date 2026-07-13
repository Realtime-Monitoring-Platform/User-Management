package com.realtime_monitoring.user_manag.service;

import com.realtime_monitoring.user_manag.dto.permissions.PermissionRequest;
import com.realtime_monitoring.user_manag.dto.permissions.PermissionResponse;


public interface PermissionService {

    PermissionResponse getAllPermissions();
    PermissionResponse createPermission(PermissionRequest PermissionRequest);
    PermissionResponse updatePermission(Long PermissionId, PermissionRequest PermissionRequest);
    void deletePermission(Long PermissionId);
    
}
