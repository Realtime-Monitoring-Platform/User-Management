package com.realtime_monitoring.user_manag.dto.tenant;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantResponse {
    
    private UUID id;
    private String name;
    private String domain;
    
    private String logoUrl;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
