package com.romanf.demo.router.transport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomKafkaProducer {

    @Value("${demo.kafka.producer.topic}")
    private String topic;

    @Value("${demo.kafka.producer.key}")
    private String key; //сообщения кладутся в одну партицию, соблюдение очередности

    private final KafkaTemplate<String, String> demoKafkaTemplate;

    public void sendMessageToKafka(String message, Long operId) {
        log.info("trying to send message...");

        List<Header> headers = new ArrayList<>();
        headers.add(new RecordHeader("operId", operId.toString().getBytes(StandardCharsets.UTF_8)));

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, null, key, message, headers);
        demoKafkaTemplate.send(producerRecord);
    }
}
