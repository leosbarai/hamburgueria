package com.github.leosbarai.hamburgueria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;
import java.util.stream.Collectors;



@ControllerAdvice
public class HamburgueriaExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MyException.class})
    public ResponseEntity handler(MyException ex) {
        return new ResponseEntity<>(Objects.nonNull(ex.getExceptionMessages()) ?
                ex.getExceptionMessages()
                        .stream()
                        .map(e -> new ErrorDTO(e.getCode(), e.getMessage()))
                        .collect(Collectors.toList()) : ex.getMyMessage(), HttpStatus.BAD_REQUEST);
    }
}
