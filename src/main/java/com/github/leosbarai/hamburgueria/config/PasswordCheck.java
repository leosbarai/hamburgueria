package com.github.leosbarai.hamburgueria.config;

import com.github.leosbarai.hamburgueria.exception.ExceptionMessages;
import com.github.leosbarai.hamburgueria.exception.MyException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Component
public class PasswordCheck {

    public void checkPasswordRules(String password) throws MyException {

        Set<ExceptionMessages> exceptionMessages = new HashSet<>();

        if (StringUtils.isEmpty(password)) {
            throw new MyException(ExceptionMessages.PASSWORD_REQUIRED);
        }

        if (password.length() < 6 || password.length() > 10) {
            exceptionMessages.add(ExceptionMessages.PASSWORD_INVALID);
        }

        if (!isDigit(password) || !isLetter(password)){
            exceptionMessages.add(ExceptionMessages.PASSWORD_DIGIT_LETTER);
        }

        if (!CollectionUtils.isEmpty(exceptionMessages)) {
            throw new MyException(exceptionMessages);
        }

    }

    private boolean isDigit(String value) {
        boolean isDigit = false;
        for (int i = 0; i < value.length(); i++) {
            if (Character.isDigit(value.charAt(i))) {
                isDigit = true;
                break;
            }
        }
        return isDigit;
    }

    private boolean isLetter(String value) {
        boolean isLetter = false;
        for (int i = 0; i < value.length(); i++) {
            if (Character.isLetter(value.charAt(i))) {
                isLetter = true;
                break;
            }
        }
        return isLetter;
    }

}
