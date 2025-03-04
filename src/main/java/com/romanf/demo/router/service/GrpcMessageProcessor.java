package com.romanf.demo.router.service;

import com.romanf.demo.router.dto.RequestMessage;
import com.romanf.demo.router.dto.RequestMessageType;
import com.romanf.demo.router.transport.GrpcClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrpcMessageProcessor implements MessageProcessor {

    private final GrpcClientService grpcClientService;

    @Override
    public boolean isSupport(RequestMessageType type) {
        return RequestMessageType.GRPC.equals(type);
    }

    @Override
    public String process(RequestMessage request) {
        return grpcClientService.sendMessage(request.getMessage());
    }
}
