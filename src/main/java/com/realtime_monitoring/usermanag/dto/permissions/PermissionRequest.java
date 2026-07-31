package com.realtime_monitoring.usermanag.dto.permissions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequest {
    private String name;
    private String description;
    private String resource;
    private String action;
    
    
}
