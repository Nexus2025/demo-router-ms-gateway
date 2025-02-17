package com.romanf.demo.router.controller;

import com.romanf.demo.router.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GatewayController {

    private final MessageService messageService;

    @PostMapping("/message")
    public String handleMessage(@RequestBody MessageRequest request) {
        return messageService.processMessage(request);
    }
}
