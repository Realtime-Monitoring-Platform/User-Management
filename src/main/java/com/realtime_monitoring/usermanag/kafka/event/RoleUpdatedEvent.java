package com.realtime_monitoring.usermanag.kafka.event;

import java.util.UUID;

public record RoleUpdatedEvent(
    DomainEvent event,
    UUID roleId,
    String name,
    String description
) {
}
