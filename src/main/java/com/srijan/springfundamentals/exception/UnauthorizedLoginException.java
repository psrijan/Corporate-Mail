package com.srijan.springfundamentals.exception;

public class UnauthorizedLoginException extends RuntimeException {

    public UnauthorizedLoginException(final String message) {
        super(message);
    }
}

