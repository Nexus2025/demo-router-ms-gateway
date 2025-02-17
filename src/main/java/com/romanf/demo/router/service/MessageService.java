package com.romanf.demo.router.service;

import com.romanf.demo.router.controller.MessageRequest;
import com.romanf.demo.router.controller.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MessageService {

    @Value("${rest.client.url}")
    private String url;

    private final RestTemplate restTemplate;
    private final CustomKafkaProducer kafkaProducer;

    public String processMessage(MessageRequest request) {
        if (MessageType.KAFKA.equals(request.getType())) {
            kafkaProducer.sendMessageToKafka(request.getMessage());
            return "Message sent to Kafka";

        } else if (MessageType.REST.equals(request.getType())) {
            restTemplate.postForObject(url, request, String.class);
            return "Message sent via REST";
        }

        return "Invalid type";
    }
}
