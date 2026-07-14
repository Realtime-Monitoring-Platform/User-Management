package com.realtime_monitoring.user_manag.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realtime_monitoring.user_manag.dto.permissions.PermissionRequest;
import com.realtime_monitoring.user_manag.dto.permissions.PermissionResponse;
import com.realtime_monitoring.user_manag.mapper.PermissionMapper;
import com.realtime_monitoring.user_manag.model.Permission;
import com.realtime_monitoring.user_manag.repository.PermissionRepository;
import com.realtime_monitoring.user_manag.service.PermissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;


    @Override
    @Transactional(readOnly = true)
    public Page<PermissionResponse> getAllPermissions(Pageable pageable) {

        return permissionRepository.findAll(pageable)
                .map(permissionMapper::toResponse);
    }


    @Override
    public PermissionResponse createPermission(PermissionRequest request) {

        Permission permission = permissionMapper.toEntity(request);

        Permission savedPermission = permissionRepository.save(permission);

        return permissionMapper.toResponse(savedPermission);
    }


    @Override
    public PermissionResponse updatePermission(
            UUID permissionId,
            PermissionRequest request) {

        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> 
                    new RuntimeException("Permission not found")
                );


        permissionMapper.updateEntityFromRequest(request, permission);

        Permission updatedPermission = permissionRepository.save(permission);

        return permissionMapper.toResponse(updatedPermission);
    }


    @Override
    public void deletePermission(UUID permissionId) {

        if (!permissionRepository.existsById(permissionId)) {
            throw new RuntimeException("Permission not found");
        }

        permissionRepository.deleteById(permissionId);
    }
    
    
}
