package com.romanf.demo.router.service;

import com.romanf.demo.router.dto.RequestMessage;
import com.romanf.demo.router.dto.RequestMessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MessageRoutesService {

    @Value("${rest.client.url}")
    private String url;

    private static final String SENT_TO_KAFKA = "Message sent to Kafka";
    private static final String SENT_VIA_REST = "Message sent via REST";

    private final RestTemplate restTemplate;
    private final CustomKafkaProducer kafkaProducer;

    public String processMessage(RequestMessage request) {

        if (RequestMessageType.KAFKA.equals(request.getType())) {
            kafkaProducer.sendMessageToKafka(request.getMessage());
            return SENT_TO_KAFKA;

        } else {
            restTemplate.postForObject(url, request, String.class);
            return SENT_VIA_REST;
        }
    }
}
