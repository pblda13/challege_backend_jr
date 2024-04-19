package com.challege.backend_jr.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic newUserTopic() {
        return TopicBuilder.name("new_user_topic")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
