package com.realtime_monitoring.user_manag.dto.role;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RoleRequest {

    private String name;

    private String description;

    private Set<UUID> permissionIds;
}