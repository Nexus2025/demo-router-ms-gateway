package com.romanf.demo.router.controller;

import com.romanf.demo.router.dto.RequestMessage;
import com.romanf.demo.router.dto.ResponseMessage;
import com.romanf.demo.router.service.MessageRoutesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GatewayController {

    private final MessageRoutesService messageRoutesService;

    @PostMapping("/message")
    public ResponseEntity<ResponseMessage> handleMessage(@Valid @RequestBody RequestMessage request) {
        String result = messageRoutesService.processMessage(request);
        return ResponseEntity.ok(ResponseMessage.builder().status(result).build());
    }
}
