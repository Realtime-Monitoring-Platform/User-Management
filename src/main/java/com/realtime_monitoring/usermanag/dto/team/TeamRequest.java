package com.realtime_monitoring.usermanag.dto.team;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequest {
    private String name;
    private String description;
    private UUID tenantId;
    private UUID teamLeaderId;

}
