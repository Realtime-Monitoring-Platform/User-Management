package com.realtime_monitoring.user_manag.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.realtime_monitoring.user_manag.dto.permissions.PermissionRequest;
import com.realtime_monitoring.user_manag.dto.permissions.PermissionResponse;
import com.realtime_monitoring.user_manag.repository.PermissionRepository;
import com.realtime_monitoring.user_manag.service.PermissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    @Override
    public List<PermissionResponse> getAllPermissions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPermissions'");
    }

    @Override
    public PermissionResponse createPermission(PermissionRequest PermissionRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPermission'");
    }

    @Override
    public PermissionResponse updatePermission(UUID PermissionId, PermissionRequest PermissionRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePermission'");
    }

    @Override
    public void deletePermission(UUID PermissionId) {
                this.permissionRepository.deleteById(PermissionId);;

    }
    
    
}
