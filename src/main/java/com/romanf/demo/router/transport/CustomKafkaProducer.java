package com.romanf.demo.router.transport;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomKafkaProducer {

    @Value("${kafka.producer.topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageToKafka(String message) {
        kafkaTemplate.send(topic, message);
    }
}
