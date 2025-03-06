package com.romanf.demo.router.transport;

import com.example.grpc.Message;
import com.example.grpc.MessageServiceGrpc;
import com.romanf.demo.router.dto.RequestMessage;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrpcClientService {

    /*
        BlockingStub - stub для синхронного общения с сервером
        клиент блокируется, до тех пор пока не получит ответ от сервера
        есть разные стабы для разных задач
     */

    @GrpcClient("grpc-service")
    private MessageServiceGrpc.MessageServiceBlockingStub messageStub;

    public String sendMessage(RequestMessage request) {
        Message.MessageRequest grpcRequest = Message.MessageRequest.newBuilder()
                .setMessage(request.getMessage())
                .setOperId(request.getOperId())
                .build();

        Message.MessageResponse response = messageStub.sendMessage(grpcRequest);
        return response.getStatus();
    }
}
