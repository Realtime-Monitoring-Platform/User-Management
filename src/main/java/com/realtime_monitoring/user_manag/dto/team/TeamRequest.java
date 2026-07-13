package com.realtime_monitoring.user_manag.dto.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequest {
    
    private String name;
    private String description;
    
    private Long tenantId;
    
}
