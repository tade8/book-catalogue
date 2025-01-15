package com.org.application.controller.exception;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.*;

@Getter
@AllArgsConstructor
@Builder
public class ExceptionResponse {
    private String message;
    private boolean status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;
}
