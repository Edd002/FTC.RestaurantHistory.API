package com.fiap.tech.challenge.global.exception;

import java.io.Serial;

public final class HashingException extends ApiException {

    @Serial
    private static final long serialVersionUID = 1L;

    public HashingException(String message) {
        super(message);
    }
}
