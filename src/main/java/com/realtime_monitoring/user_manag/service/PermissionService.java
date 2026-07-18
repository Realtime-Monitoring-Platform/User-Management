package com.realtime_monitoring.user_manag.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.realtime_monitoring.user_manag.dto.permissions.PermissionRequest;
import com.realtime_monitoring.user_manag.dto.permissions.PermissionResponse;


public interface PermissionService {
    
    Page<PermissionResponse> getAllPermissions(Pageable pageable);
    PermissionResponse createPermission(PermissionRequest PermissionRequest);
    PermissionResponse updatePermission(UUID PermissionId, PermissionRequest PermissionRequest);
    void deletePermission(UUID PermissionId);

}
