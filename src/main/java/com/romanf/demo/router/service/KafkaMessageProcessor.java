package com.romanf.demo.router.service;

import com.romanf.demo.router.dto.RequestMessage;
import com.romanf.demo.router.dto.RequestMessageType;
import com.romanf.demo.router.transport.CustomKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessageProcessor implements MessageProcessor{

    private final CustomKafkaProducer kafkaProducer;

    private static final String SENT_TO_KAFKA = "Message sent to Kafka";

    @Override
    public boolean isSupport(RequestMessageType type) {
        return RequestMessageType.KAFKA.equals(type);
    }

    @Override
    public String process(RequestMessage request) {
        kafkaProducer.sendMessageToKafka(request.getMessage(), request.getOperId());
        return SENT_TO_KAFKA;
    }
}
