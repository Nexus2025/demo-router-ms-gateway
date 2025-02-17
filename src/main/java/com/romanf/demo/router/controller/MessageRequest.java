package com.romanf.demo.router.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {

    private MessageType type;
    private String message;
}
