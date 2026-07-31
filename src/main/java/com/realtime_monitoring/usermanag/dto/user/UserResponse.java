package com.realtime_monitoring.usermanag.dto.user;

import java.time.LocalDateTime;
import java.util.UUID;

import com.realtime_monitoring.usermanag.dto.tenant.TenantDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    
    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String avatarUrl;
    private String status;
    private LocalDateTime lastLogin;
    
    private UUID tenantId;
    private TenantDto tenant;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
