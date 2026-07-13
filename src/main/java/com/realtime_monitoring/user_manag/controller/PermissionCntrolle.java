package com.realtime_monitoring.user_manag.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_monitoring.user_manag.service.PermissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionCntrolle {
    private final PermissionService permissionService;

    
}
