package com.realtime_monitoring.user_manag.dto.team;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {

    private Long id;
    
    private String name;
    private String description;
    private Long tenantId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}