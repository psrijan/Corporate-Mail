package com.srijan.springfundamentals.exception;

public class ActionDeniedException extends RuntimeException {

    public ActionDeniedException(final String msg) {
        super(msg);
    }
}
