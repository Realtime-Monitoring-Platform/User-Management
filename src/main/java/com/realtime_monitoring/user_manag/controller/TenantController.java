package com.realtime_monitoring.user_manag.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_monitoring.user_manag.dto.tenant.TenantRequest;
import com.realtime_monitoring.user_manag.dto.tenant.TenantResponse;
import com.realtime_monitoring.user_manag.service.TenantService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/tenants")
@RequiredArgsConstructor

public class TenantController {
    private final TenantService tenantService; 

    @PostMapping
    public ResponseEntity<TenantResponse> createTenant(@RequestBody TenantRequest entity) {
        System.out.println("Received request to create tenant::::::::::::::::::::::::::::::::::::::::" + entity);
        TenantResponse tenantResponse = this.tenantService.createTenant(entity);
        System.out.println("Created tenant response::::::::::::::::::::::::::::::::::::::::" + tenantResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(tenantResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantResponse> getTenantById(@PathVariable UUID id) {
        TenantResponse tenantResponse = this.tenantService.getTenantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(tenantResponse);
    }
    
    @GetMapping
    public ResponseEntity<Page<TenantResponse>> getTenants(
        @PageableDefault(
            page = 0,
            size = 10,
            sort = "createdAt"
            // direction = Sort.Direction.DESC
        )
        Pageable pageable
    ) {
        Page<TenantResponse> tenantResponses = this.tenantService.getAllTenants(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(tenantResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenantById(@PathVariable UUID id) {
        this.tenantService.deleteTenant(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



    
    
}
