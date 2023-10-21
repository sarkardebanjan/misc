package org.trials.kafka.kafkasample.config;

import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaWithBeansConfig {

    @Value("${camel.component.kafka.brokers}")
    private String brokers;

    @Value("${camel.component.kafka.group-id}")
    private String groupId;

    @Bean
    public KafkaConfiguration kafkaConfigurationInternal() {
        KafkaConfiguration kafkaConfigurationInternal = new KafkaConfiguration();
        kafkaConfigurationInternal.setBrokers(brokers);
        kafkaConfigurationInternal.setGroupId(groupId);
        return kafkaConfigurationInternal;
    }

    @Bean
    public KafkaComponent kafkaInternal() {
        KafkaComponent kafkaInternal = new KafkaComponent();
        kafkaInternal.setConfiguration(kafkaConfigurationInternal());
        return kafkaInternal;
    }

}
