package com.realtime_monitoring.user_manag.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(JsonSerializer.TYPE_MAPPINGS,
                "userCreated:com.realtime_monitoring.user_manag.kafka.event.UserCreatedEvent," +
                        "userUpdated:com.realtime_monitoring.user_manag.kafka.event.UserUpdatedEvent," +
                        "userDeleted:com.realtime_monitoring.user_manag.kafka.event.UserDeletedEvent," +
                        "teamCreated:com.realtime_monitoring.user_manag.kafka.event.TeamCreatedEvent," +
                        "teamUpdated:com.realtime_monitoring.user_manag.kafka.event.TeamUpdatedEvent," +
                        "teamDeleted:com.realtime_monitoring.user_manag.kafka.event.TeamDeletedEvent");
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
