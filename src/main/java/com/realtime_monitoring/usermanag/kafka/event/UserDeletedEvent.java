package com.realtime_monitoring.usermanag.kafka.event;

import java.util.UUID;

public record UserDeletedEvent(
    DomainEvent event,
        UUID id
) {}
