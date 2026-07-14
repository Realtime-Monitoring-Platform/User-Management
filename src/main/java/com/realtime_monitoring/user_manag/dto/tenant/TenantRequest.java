package com.realtime_monitoring.user_manag.dto.tenant;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantRequest {
    
    private String name;
    private String domain;
    
    private String logoUrl;
    private String status;
    
    
}
