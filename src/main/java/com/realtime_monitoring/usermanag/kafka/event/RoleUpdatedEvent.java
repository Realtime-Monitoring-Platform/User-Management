package com.realtime_monitoring.usermanag.kafka.event;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record RoleUpdatedEvent(
    DomainEvent event,
    UUID roleId,
    String name,
    String description,
    Set<UUID> permissionIds
) {
}
