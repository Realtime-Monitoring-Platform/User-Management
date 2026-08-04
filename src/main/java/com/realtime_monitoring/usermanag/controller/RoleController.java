package com.realtime_monitoring.usermanag.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_monitoring.usermanag.dto.role.AssignPermissionsRequest;
import com.realtime_monitoring.usermanag.dto.role.RoleRequest;
import com.realtime_monitoring.usermanag.dto.role.RoleResponse;
import com.realtime_monitoring.usermanag.service.RoleService;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<Page<RoleResponse>> getAllRoles(@PageableDefault(page = 0, size = 10, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(roleService.getAllRoles(pageable));
    }

    @PostMapping
    public ResponseEntity<RoleResponse> createRole(@RequestBody RoleRequest request) {
        System.out.println("Creating role controller:///////////////////////////// " + request);
        RoleResponse response = roleService.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponse> updateRole(@PathVariable UUID id,@RequestBody RoleRequest request) {
        RoleResponse response = roleService.updateRole(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable UUID id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/permissions")
    public ResponseEntity<RoleResponse> assignPermissionsToRole(
            @PathVariable UUID id,
            @RequestBody AssignPermissionsRequest request) {
        RoleResponse response = roleService.assignPermissionsToRole(id, request.getPermissionIds());
        return ResponseEntity.ok(response);
    }
}
