package com.romanf.demo.router.transport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomKafkaProducer {

    @Value("${demo.kafka.producer.topic}")
    private String topic;

    private final KafkaTemplate<String, String> demoKafkaTemplate;

    public void sendMessageToKafka(String message) {
        log.info("trying to send message...");
        demoKafkaTemplate.send(topic, message);
    }
}
