package com.realtime_monitoring.usermanag.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realtime_monitoring.usermanag.dto.permissions.PermissionRequest;
import com.realtime_monitoring.usermanag.dto.permissions.PermissionResponse;
import com.realtime_monitoring.usermanag.kafka.PermissionProducer;
import com.realtime_monitoring.usermanag.mapper.PermissionMapper;
import com.realtime_monitoring.usermanag.model.Permission;
import com.realtime_monitoring.usermanag.repository.PermissionRepository;
import com.realtime_monitoring.usermanag.service.PermissionService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;
    private final PermissionProducer permissionProducer;


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
        permissionProducer.sendPermissionCreation(savedPermission);

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
        permissionProducer.sendPermissionUpdate(updatedPermission);

        return permissionMapper.toResponse(updatedPermission);
    }


    @Override
    public void deletePermission(UUID permissionId) {

        if (!permissionRepository.existsById(permissionId)) {
            throw new RuntimeException("Permission not found");
        }

        permissionRepository.deleteById(permissionId);
        permissionProducer.sendPermissionDeleted(permissionId);
    }
    
    
}
