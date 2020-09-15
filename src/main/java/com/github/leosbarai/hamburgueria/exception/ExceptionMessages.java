package com.github.leosbarai.hamburgueria.exception;

public enum ExceptionMessages {

    /**
     * User Messages
     **/

    PASSWORD_INVALID("PASS-1", "A senha deve conter de 6 a 10 caracteres."),
    PASSWORD_REQUIRED("PASS-2", "A senha é obrigatória."),
    PASSWORD_DIGIT_LETTER("PASS-3", "A senha deverá conter ao menos 1 letra e um 1 dígito."),

    ;

    String code;
    String message;

    ExceptionMessages(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
