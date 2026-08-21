package com.realtime_monitoring.usermanag.dto.role;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {

    private String name;

    private String description;

    private Set<UUID> permissionIds;
}