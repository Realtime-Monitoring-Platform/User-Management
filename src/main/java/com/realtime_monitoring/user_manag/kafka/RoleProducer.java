package com.realtime_monitoring.user_manag.kafka;

import com.realtime_monitoring.user_manag.kafka.event.TeamCreatedEvent;
import com.realtime_monitoring.user_manag.kafka.event.TeamUpdatedEvent;
import com.realtime_monitoring.user_manag.kafka.event.DomainEvent;
import com.realtime_monitoring.user_manag.kafka.event.RoleCreatedEvent;
import com.realtime_monitoring.user_manag.kafka.event.RoleUpdatedEvent;
import com.realtime_monitoring.user_manag.kafka.event.UserCreatedEvent;
import com.realtime_monitoring.user_manag.kafka.event.UserDeletedEvent;
import com.realtime_monitoring.user_manag.kafka.event.UserUpdatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

import com.realtime_monitoring.user_manag.model.User;
import com.realtime_monitoring.user_manag.model.UserStatus;
import com.realtime_monitoring.user_manag.model.Role;
import com.realtime_monitoring.user_manag.model.Team;

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
                role.getDescription()
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
                role.getDescription()
                );
        

        log.info("sending role update event::::::::::::::: {}", roleEvent);
        kafkaTemplate.send("role-events-v1", role.getId().toString(), roleEvent);
    }

    public void sendTeamCreation(Team team) {
        DomainEvent event = new DomainEvent(
                UUID.randomUUID(),
                "TEAM_CREATED",
                team.getId(),
                "TEAM",
                Instant.now());
        TeamCreatedEvent teamEvent = new TeamCreatedEvent(
                team.getId(),
                event,
                team.getName(),
                team.getDescription(),
                team.getTenantId());
        log.info("sending team creation event::::::::::::: {}", teamEvent);
        kafkaTemplate.send("team-events-v2", team.getId().toString(), teamEvent);
    }


}
