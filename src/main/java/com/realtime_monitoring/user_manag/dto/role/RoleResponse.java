package com.realtime_monitoring.user_manag.dto.role;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.realtime_monitoring.user_manag.dto.permissions.PermissionResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {
    
    private UUID id;
    private String name;
    private String description;
    private Set<PermissionResponse> permissions;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
