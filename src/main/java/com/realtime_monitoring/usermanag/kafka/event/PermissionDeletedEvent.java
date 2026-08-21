package com.realtime_monitoring.usermanag.kafka.event;

import java.util.UUID;

public record PermissionDeletedEvent(
    DomainEvent event,
    UUID permissionId
) {
}