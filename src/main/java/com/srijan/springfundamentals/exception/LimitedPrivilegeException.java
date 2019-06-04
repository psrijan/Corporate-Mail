package com.srijan.springfundamentals.exception;

public class LimitedPrivilegeException extends RuntimeException {

    public LimitedPrivilegeException(final String msg) {
        super(msg);
    }
}
