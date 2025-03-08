package com.romanf.demo.router.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class KafkaProperties {

    private String bootstrapServers;
    private String acksConfig;
    private Integer deliveryTimeoutMsConfig;
    private Integer requestTimeoutMsConfig;
    private Integer maxBlockMsConfig;
}
