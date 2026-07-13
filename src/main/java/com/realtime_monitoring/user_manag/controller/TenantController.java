package com.realtime_monitoring.user_manag.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_monitoring.user_manag.imp.TenantServiceImpl;
import com.realtime_monitoring.user_manag.service.TenantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {
    private final TenantService tenantService; 
    
}
