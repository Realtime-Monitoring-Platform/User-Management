package com.realtime_monitoring.usermanag.kafka.event;

import java.util.UUID;

public record TeamDeletedEvent(
    DomainEvent event,
        UUID id
) {}


