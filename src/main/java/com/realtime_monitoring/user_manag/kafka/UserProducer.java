package com.realtime_monitoring.user_manag.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.realtime_monitoring.user_manag.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserProducer {
    private final KafkaTemplate<String, User> kafkaTemplate;
    public void sendUserCreation(User user ){
        log.info("sending uuser creation");
        Message<User> userMessage= MessageBuilder.withPayload(user)
            .setHeader(TOPIC, "user-creation").build();
        kafkaTemplate.send(userMessage);
    }
    
}
