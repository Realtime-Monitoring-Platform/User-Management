package com.realtime_monitoring.user_manag.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_monitoring.user_manag.dto.permissions.PermissionRequest;
import com.realtime_monitoring.user_manag.dto.permissions.PermissionResponse;
import com.realtime_monitoring.user_manag.service.PermissionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<Page<PermissionResponse>> getAllPermissions(@PageableDefault(page = 0, size = 10, sort = "createdAt") Pageable pageable) {

        return ResponseEntity.ok(permissionService.getAllPermissions(pageable));
    }

    @PostMapping
    public ResponseEntity<PermissionResponse> createPermission( @Valid @RequestBody PermissionRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.createPermission(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionResponse> updatePermission(@PathVariable UUID id,@Valid @RequestBody PermissionRequest request) {

        return ResponseEntity.ok(permissionService.updatePermission(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable UUID id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }
}