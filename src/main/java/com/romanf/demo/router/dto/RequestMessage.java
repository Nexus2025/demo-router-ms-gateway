package com.romanf.demo.router.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestMessage {

    @NotNull
    @Min(0)
    private Long operId;

    @NotNull
    private RequestMessageType type;

    @NotBlank
    @NotNull
    private String message;

}
