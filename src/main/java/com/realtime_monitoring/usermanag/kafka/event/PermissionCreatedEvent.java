package com.realtime_monitoring.usermanag.kafka.event;

import java.util.UUID;

public record PermissionCreatedEvent(
    DomainEvent event,
    UUID permissionId,
    String name,
    String description,
    String resource,
    String action
) {
}