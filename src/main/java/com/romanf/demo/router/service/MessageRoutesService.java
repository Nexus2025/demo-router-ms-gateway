package com.romanf.demo.router.service;

import com.romanf.demo.router.dto.RequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageRoutesService {

    private final List<MessageProcessor> processors;

    public String processMessage(RequestMessage request) {
        return processors.stream()
                .filter(p -> p.isSupport(request.getType()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported message type: " + request.getType()))
                .process(request);
    }
}
