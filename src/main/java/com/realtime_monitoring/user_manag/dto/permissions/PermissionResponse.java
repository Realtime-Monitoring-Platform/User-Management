package com.realtime_monitoring.user_manag.dto.permissions;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {
    
    private UUID id;
    private String name;
    private String description;
    private String resource;
    
    private String action;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
