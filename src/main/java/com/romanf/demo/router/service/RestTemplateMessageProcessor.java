package com.romanf.demo.router.service;

import com.romanf.demo.router.dto.RequestMessage;
import com.romanf.demo.router.dto.RequestMessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestTemplateMessageProcessor implements MessageProcessor {

    private final RestTemplate restTemplate;

    @Value("${rest.client.url}")
    private String url;

    private static final String SENT_VIA_REST = "Message sent via REST";

    @Override
    public boolean isSupport(RequestMessageType type) {
        return RequestMessageType.REST_TEMPLATE.equals(type);
    }

    @Override
    public String process(RequestMessage request) {
        restTemplate.postForObject(url, request, String.class);
        return SENT_VIA_REST;
    }
}
