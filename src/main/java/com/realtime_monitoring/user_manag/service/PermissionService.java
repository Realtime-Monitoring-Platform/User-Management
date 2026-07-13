package com.realtime_monitoring.user_manag.service;

import java.util.List;
import java.util.UUID;

import com.realtime_monitoring.user_manag.dto.permissions.PermissionRequest;
import com.realtime_monitoring.user_manag.dto.permissions.PermissionResponse;


public interface PermissionService {

    List<PermissionResponse> getAllPermissions();
    PermissionResponse createPermission(PermissionRequest PermissionRequest);
    PermissionResponse updatePermission(UUID PermissionId, PermissionRequest PermissionRequest);
    void deletePermission(UUID PermissionId);
    
}
