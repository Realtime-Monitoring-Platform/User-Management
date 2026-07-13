package com.realtime_monitoring.user_manag.dto.user;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String avatarUrl;
    private String status;
    private LocalDateTime lastLogin;
    
    private Long tenantId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
