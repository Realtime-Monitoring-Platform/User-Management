package com.realtime_monitoring.user_manag.kafka.event;

import java.util.UUID;

public record TeamDeletedEvent(
    DomainEvent event,
        UUID id
) {}


