package com.romanf.demo.router.service;

import com.romanf.demo.router.dto.RequestMessage;
import com.romanf.demo.router.dto.RequestMessageType;

public interface MessageProcessor {

    boolean isSupport(RequestMessageType type);

    String process(RequestMessage request);
}
