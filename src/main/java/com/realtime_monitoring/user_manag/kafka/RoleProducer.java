package com.realtime_monitoring.user_manag.kafka;

import com.realtime_monitoring.user_manag.kafka.event.TeamCreatedEvent;
import com.realtime_monitoring.user_manag.kafka.event.TeamUpdatedEvent;
import com.realtime_monitoring.user_manag.kafka.event.DomainEvent;
import com.realtime_monitoring.user_manag.kafka.event.RoleCreatedEvent;
import com.realtime_monitoring.user_manag.kafka.event.RoleUpdateEvent;
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

    

}
