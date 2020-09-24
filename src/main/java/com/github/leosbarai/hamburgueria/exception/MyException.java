package com.github.leosbarai.hamburgueria.exception;

import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class MyException extends Exception {

    private Set<ExceptionMessages> exceptionMessages;

    public MyException(Set<ExceptionMessages> exceptionMessages) {
        this.exceptionMessages = exceptionMessages;
    }

    public MyException(ExceptionMessages exceptionMessages) {
        this.exceptionMessages = Collections.singleton(exceptionMessages);
    }

    public Set<ExceptionMessages> getExceptionMessages() {
        return exceptionMessages;
    }

    public void setExceptionMessages(Set<ExceptionMessages> exceptionMessages) {
        this.exceptionMessages = exceptionMessages;
    }

    public String getMyMessage() {
        if (StringUtils.isEmpty(super.getMessage())) {
            return exceptionMessages
                    .stream()
                    .map(ExceptionMessages::getCode)
                    .collect(Collectors.joining("|"));
        }

        return super.getMessage();
    }
}
