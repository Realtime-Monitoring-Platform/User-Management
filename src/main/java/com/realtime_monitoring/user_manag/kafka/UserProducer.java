package com.realtime_monitoring.user_manag.kafka;

import com.realtime_monitoring.user_manag.kafka.event.TeamCreatedEvent;
import com.realtime_monitoring.user_manag.kafka.event.TeamUpdatedEvent;
import com.realtime_monitoring.user_manag.kafka.event.DomainEvent;
import com.realtime_monitoring.user_manag.kafka.event.UserCreatedEvent;
import com.realtime_monitoring.user_manag.kafka.event.UserUpdatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

import com.realtime_monitoring.user_manag.model.User;
import com.realtime_monitoring.user_manag.model.UserStatus;
import com.realtime_monitoring.user_manag.model.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendUserCreation(User user) {
        DomainEvent event = new DomainEvent(
                UUID.randomUUID(),
                "USER_CREATED",
                user.getId(),
                "USER",
                Instant.now());

        UserCreatedEvent userEvent = new UserCreatedEvent(
                event,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getAvatarUrl(),
                user.getPassword(),
                user.getAddress(),
                user.getStatus() != null ? user.getStatus().name() : null,
                user.getTenantId(),
                user.getRole() != null ? user.getRole().getId() : null,
                user.getTeam() != null ? user.getTeam().getId() : null);

        log.info("sending user creation event::::::::::::::: {}", userEvent);
        kafkaTemplate.send("user-events-v5", user.getId().toString(), userEvent);
    }

    public void sendUserUpdate(User user) {
        DomainEvent event = new DomainEvent(
                UUID.randomUUID(),
                "USER_UPDATED",
                user.getId(),
                "USER",
                Instant.now());


        UserUpdatedEvent userEvent = new UserUpdatedEvent(
                event,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getAvatarUrl(),
                user.getPassword(),
                user.getAddress(),
                user.getStatus() != null ? user.getStatus().name() : null,
                user.getTenantId(),
                user.getRole() != null ? user.getRole().getId() : null,
                user.getTeam() != null ? user.getTeam().getId() : null);
        log.info("sending user update event::::::::::::::: {}", userEvent);
        kafkaTemplate.send("user-events-v5", user.getId().toString(), userEvent);
    }

    public void sendTeamCreation(Team team) {
        DomainEvent event = new DomainEvent(
                UUID.randomUUID(),
                "TEAM_CREATED",
                team.getId(),
                "TEAM",
                Instant.now());
        TeamCreatedEvent teamEvent = new TeamCreatedEvent(
                event,
                team.getName(),
                team.getDescription(),
                team.getTenantId());
        log.info("sending team creation event::::::::::::: {}", teamEvent);
        kafkaTemplate.send("team-events", team.getId().toString(), teamEvent);
    }

    public void sendTeamUpdate(Team team) {
        DomainEvent event = new DomainEvent(
                UUID.randomUUID(),
                "TEAM_UPDATED",
                team.getId(),
                "TEAM",
                Instant.now());
        TeamUpdatedEvent teamEvent = new TeamUpdatedEvent(
                event,
                team.getName(),
                team.getDescription(),
                team.getTenantId());
        log.info("sending team update event::::::::::::::: {}", teamEvent);
        kafkaTemplate.send("team-events", team.getId().toString(), teamEvent);
    }
    
}
