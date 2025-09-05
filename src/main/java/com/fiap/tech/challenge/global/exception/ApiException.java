package com.fiap.tech.challenge.global.exception;

import java.io.Serial;

public abstract class ApiException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }
}
