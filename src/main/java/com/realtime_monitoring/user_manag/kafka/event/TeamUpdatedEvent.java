package com.realtime_monitoring.user_manag.kafka.event;

import java.util.UUID;

public record TeamUpdatedEvent(
    UUID id,
    DomainEvent event,
    String name,
    String description,
    UUID tenantId
) {}
