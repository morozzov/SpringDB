package com.example.springdb.exceptions;

import com.example.springdb.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerInterceptor {

    @ExceptionHandler(PSBusinessException.class)
    @ResponseBody
    public ErrorDTO handleBusinessException(PSBusinessException psBusinessException) {
        log.error("exception caught by advice {}", psBusinessException.getMessage());
        return ErrorDTO.builder()
                .statusCode("400")
                .message(psBusinessException.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorDTO handleGlobal(Exception psBusinessException) {
        log.error("exception caught by advice {}", psBusinessException.getMessage());
        return ErrorDTO.builder()
                .statusCode("500")
                .message(psBusinessException.getMessage())
                .build();
    }
}
