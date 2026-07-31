package com.realtime_monitoring.usermanag.kafka.event;

import java.util.UUID;

public record RoleCreatedEvent(
    DomainEvent event,
    UUID roleId,
    String name,
    String description
) {
}
