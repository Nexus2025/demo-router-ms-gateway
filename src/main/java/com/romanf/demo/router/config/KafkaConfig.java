package com.romanf.demo.router.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean(name = "demoKafkaProperties")
    @ConfigurationProperties(prefix = "demo.kafka.config")
    KafkaProperties demoKafkaProperties() {
        return new KafkaProperties();
    }

    @Bean(name = "demoKafkaTemplate")
    public KafkaTemplate<String, String> demoKafkaTemplate(
            @Qualifier("demoProducerFactory") ProducerFactory<String, String> demoProducerFactory)
    {
        return new KafkaTemplate<>(demoProducerFactory);
    }

    @Bean(name = "demoProducerFactory")
    public ProducerFactory<String, String> demoProducerFactory(
            @Qualifier("demoKafkaProperties") KafkaProperties demoKafkaProperties)
    {
        Map<String, Object> kafkaConfig = new HashMap<>();
        kafkaConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, demoKafkaProperties.getBootstrapServers());
        kafkaConfig.put(ProducerConfig.ACKS_CONFIG, demoKafkaProperties.getAcksConfig());
        kafkaConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaConfig.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, demoKafkaProperties.getDeliveryTimeoutMsConfig());
        kafkaConfig.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, demoKafkaProperties.getRequestTimeoutMsConfig());
        kafkaConfig.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, demoKafkaProperties.getMaxBlockMsConfig());

        return new DefaultKafkaProducerFactory<>(kafkaConfig);
    }
}
