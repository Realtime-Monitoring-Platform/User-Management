package com.realtime_monitoring.usermanag.kafka;

import java.time.Instant;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.realtime_monitoring.usermanag.kafka.event.DomainEvent;
import com.realtime_monitoring.usermanag.kafka.event.PermissionCreatedEvent;
import com.realtime_monitoring.usermanag.kafka.event.PermissionDeletedEvent;
import com.realtime_monitoring.usermanag.kafka.event.PermissionUpdatedEvent;
import com.realtime_monitoring.usermanag.model.Permission;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendPermissionCreation(Permission permission) {
        DomainEvent event = new DomainEvent(
                UUID.randomUUID(),
                "PERMISSION_CREATED",
                permission.getId(),
                "PERMISSION",
                Instant.now());

        PermissionCreatedEvent permissionEvent = new PermissionCreatedEvent(
                event,
                permission.getId(),
                permission.getName(),
                permission.getDescription(),
                permission.getResource(),
                permission.getAction());

        log.info("Sending permission creation event::::::::::::::: {}", permissionEvent);
        kafkaTemplate.send("permission-events-v1", permission.getId().toString(), permissionEvent);
    }

    public void sendPermissionUpdate(Permission permission) {
        DomainEvent event = new DomainEvent(
                UUID.randomUUID(),
                "PERMISSION_UPDATED",
                permission.getId(),
                "PERMISSION",
                Instant.now());

        PermissionUpdatedEvent permissionEvent = new PermissionUpdatedEvent(
                event,
                permission.getId(),
                permission.getName(),
                permission.getDescription(),
                permission.getResource(),
                permission.getAction());

        log.info("Sending permission update event::::::::::::::: {}", permissionEvent);
        kafkaTemplate.send("permission-events-v1", permission.getId().toString(), permissionEvent);
    }

    public void sendPermissionDeleted(UUID permissionId) {
        DomainEvent event = new DomainEvent(
                UUID.randomUUID(),
                "PERMISSION_DELETED",
                permissionId,
                "PERMISSION",
                Instant.now());

        PermissionDeletedEvent permissionEvent = new PermissionDeletedEvent(
                event,
                permissionId);

        log.info("Sending permission deletion event::::::::::::::: {}", permissionEvent);
        kafkaTemplate.send("permission-events-v1", permissionId.toString(), permissionEvent);
    }
}