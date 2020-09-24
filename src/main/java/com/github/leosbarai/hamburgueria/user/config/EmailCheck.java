package com.github.leosbarai.hamburgueria.user.config;

import com.github.leosbarai.hamburgueria.exception.ExceptionMessages;
import com.github.leosbarai.hamburgueria.exception.MyException;
import com.github.leosbarai.hamburgueria.user.entity.User;
import com.github.leosbarai.hamburgueria.user.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class EmailCheck {

    @Autowired
    private UserJpaRepository repository;

    public void checkEmailRules(String email) throws MyException {

        if (StringUtils.isEmpty(email)) {
            throw new MyException(ExceptionMessages.USER_EMAIL_REQUIRED);
        }

        User user = repository.emailValidate(email);

        if (user != null) {
            throw new MyException(ExceptionMessages.USER_EMAIL_EXISTING);
        }

    }
}
