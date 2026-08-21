package com.realtime_monitoring.usermanag.dto.team;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {
    private UUID id;
    private String name;
    private String description;
    private UUID tenantId;
    private UUID teamLeaderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}