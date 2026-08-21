package com.realtime_monitoring.usermanag.kafka;

import com.realtime_monitoring.usermanag.kafka.event.TeamCreatedEvent;
import com.realtime_monitoring.usermanag.kafka.event.TeamDeletedEvent;
import com.realtime_monitoring.usermanag.kafka.event.TeamUpdatedEvent;
import com.realtime_monitoring.usermanag.kafka.event.DomainEvent;
import com.realtime_monitoring.usermanag.kafka.event.RoleDeletedEvent;
import com.realtime_monitoring.usermanag.kafka.event.UserCreatedEvent;
import com.realtime_monitoring.usermanag.kafka.event.UserDeletedEvent;
import com.realtime_monitoring.usermanag.kafka.event.UserUpdatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

import com.realtime_monitoring.usermanag.model.User;
import com.realtime_monitoring.usermanag.model.UserStatus;
import com.realtime_monitoring.usermanag.model.Team;

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

        log.info("create user :::::::::::::::::::::::::::::::: {}", userEvent);
        kafkaTemplate.send("user-events-v8", user.getId().toString(), userEvent);
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
                user.getTeam() != null ? user.getTeam().getId() : null
                
        );
        log.info("sending user update event::::::::::::::: {}", userEvent);
        kafkaTemplate.send("user-events-v8", user.getId().toString(), userEvent);
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
                team.getTenantId(),
                team.getTeamLeaderId().getId()
        
        
                );
        log.info("sending team creation event::::::::::::: {}", teamEvent);
        kafkaTemplate.send("team-events-v3", team.getId().toString(), teamEvent);
    }

    public void sendUserDeleted(UUID userId) {

        DomainEvent event = new DomainEvent(
                UUID.randomUUID(),
                "USER_DELETED",
                userId,
                "USER",
                Instant.now());

        UserDeletedEvent userEvent = new UserDeletedEvent(
                event,
                userId);

        log.info("Sending user deletion event: {}", userEvent);

        kafkaTemplate.send(
                "user-events-v8",
                userId.toString(),
                userEvent);
    }

    public void sendTeamUpdate(Team team) {
        DomainEvent event = new DomainEvent(
                UUID.randomUUID(),
                "TEAM_UPDATED",
                team.getId(),
                "TEAM",
                Instant.now());
        TeamUpdatedEvent teamEvent = new TeamUpdatedEvent(
                team.getId(),
                event,
                team.getName(),
                team.getDescription(),
                team.getTenantId(),
                team.getTeamLeaderId().getId()
        
        
                );
        log.info("sending team update event::::::::::::::: {}", teamEvent);
        kafkaTemplate.send("team-events-v3", team.getId().toString(), teamEvent);
    }

   

    public void sendTeamDeleted(UUID TeamId) {
                DomainEvent event = new DomainEvent(
                                UUID.randomUUID(),
                                "Team_DELETED",
                                TeamId,
                                "Team",
                                Instant.now());

                TeamDeletedEvent TeamEvent = new TeamDeletedEvent(

                                event,
                                TeamId);

                log.info("sending Team deletion event::::::::::::::: {}", TeamEvent);
                kafkaTemplate.send("team-events-v3", TeamId.toString(), TeamEvent);
        }

}
