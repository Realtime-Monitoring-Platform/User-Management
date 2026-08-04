package com.realtime_monitoring.usermanag.kafka.event;

import java.util.UUID;

public record TeamCreatedEvent(
    UUID id,
    DomainEvent event,
    String name,
    String description,
    UUID tenantId,
    UUID teamLeaderId
) {}
