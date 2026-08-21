package com.realtime_monitoring.usermanag.kafka.event;

import java.util.UUID;

public record RoleDeletedEvent(
    DomainEvent event,
        UUID id
) {}


