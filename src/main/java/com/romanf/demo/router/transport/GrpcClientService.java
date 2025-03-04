package com.romanf.demo.router.transport;

import com.example.grpc.Message;
import com.example.grpc.MessageServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrpcClientService {

    @GrpcClient("grpc-server")
    private MessageServiceGrpc.MessageServiceBlockingStub messageStub;

    public String sendMessage(String message) {
        Message.MessageRequest request = Message.MessageRequest.newBuilder()
                .setContent(message)
                .build();

        Message.MessageResponse response = messageStub.sendMessage(request);
        return response.getStatus();
    }
}
