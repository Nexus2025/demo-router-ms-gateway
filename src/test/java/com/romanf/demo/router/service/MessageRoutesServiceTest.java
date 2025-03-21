package com.romanf.demo.router.service;

import com.romanf.demo.router.dto.RequestMessage;
import com.romanf.demo.router.dto.RequestMessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageRoutesServiceTest {

    private MessageRoutesService messageRoutesService;

    @Mock
    private MessageProcessor messageProcessor;

    private static final String EXPECTED_RESULT = "PROCESSED";

    @BeforeEach
    void setUp() {
        List<MessageProcessor> processors = List.of(this.messageProcessor);
        messageRoutesService = new MessageRoutesService(processors);
    }

    @Test
    void processMessage() {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setType(RequestMessageType.GRPC);

        when(messageProcessor.isSupport(any())).thenReturn(true);
        when(messageProcessor.process(eq(requestMessage))).thenReturn(EXPECTED_RESULT);

        assertEquals(EXPECTED_RESULT, messageRoutesService.processMessage(requestMessage));
    }
}