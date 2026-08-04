package com.realtime_monitoring.usermanag.kafka;

import com.realtime_monitoring.usermanag.kafka.event.TeamCreatedEvent;
import com.realtime_monitoring.usermanag.kafka.event.TeamUpdatedEvent;
import com.realtime_monitoring.usermanag.kafka.event.DomainEvent;
import com.realtime_monitoring.usermanag.kafka.event.RoleCreatedEvent;
import com.realtime_monitoring.usermanag.kafka.event.RoleDeletedEvent;
import com.realtime_monitoring.usermanag.kafka.event.RoleUpdatedEvent;
import com.realtime_monitoring.usermanag.kafka.event.UserCreatedEvent;
import com.realtime_monitoring.usermanag.kafka.event.UserDeletedEvent;
import com.realtime_monitoring.usermanag.kafka.event.UserUpdatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

import com.realtime_monitoring.usermanag.model.User;
import com.realtime_monitoring.usermanag.model.UserStatus;
import com.realtime_monitoring.usermanag.model.Role;
import com.realtime_monitoring.usermanag.model.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleProducer {
        private final KafkaTemplate<String, Object> kafkaTemplate;

        public void sendRoleCreation(Role role) {
                DomainEvent event = new DomainEvent(
                                UUID.randomUUID(),
                                "ROLE_CREATED",
                                role.getId(),
                                "ROLE",
                                Instant.now());

                RoleCreatedEvent roleEvent = new RoleCreatedEvent(
                                event,
                                role.getId(),
                                role.getName(),
                                role.getDescription(),
                                role.getPermissions().stream().map(p -> p.getId()).collect(java.util.stream.Collectors.toSet())
                        );

                log.info("sending role creation event::::::::::::::: {}", roleEvent);
                kafkaTemplate.send("role-events-v1", role.getId().toString(), roleEvent);
        }

        public void sendRoleUpdate(Role role) {
                DomainEvent event = new DomainEvent(
                                UUID.randomUUID(),
                                "ROLE_UPDATED",
                                role.getId(),
                                "ROLE",
                                Instant.now());

                RoleUpdatedEvent roleEvent = new RoleUpdatedEvent(
                                event,
                                role.getId(),
                                role.getName(),
                                role.getDescription(),
                                role.getPermissions().stream().map(p -> p.getId()).collect(java.util.stream.Collectors.toSet())
                        );

                log.info("sending role update event::::::::::::::: {}", roleEvent);
                kafkaTemplate.send("role-events-v1", role.getId().toString(), roleEvent);
        }

        public void sendRoleDeleted(UUID roleId) {
                DomainEvent event = new DomainEvent(
                                UUID.randomUUID(),
                                "ROLE_DELETED",
                                roleId,
                                "ROLE",
                                Instant.now());

                RoleDeletedEvent roleEvent = new RoleDeletedEvent(

                                event,
                                roleId);

                log.info("sending role deletion event::::::::::::::: {}", roleEvent);
                kafkaTemplate.send("role-events-v1", roleId.toString(), roleEvent);
        }

}
