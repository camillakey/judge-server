package com.judge_server.web.exception.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException() {
    }

    public EmailAlreadyExistException(String s) {
        super(s);
    }

    public EmailAlreadyExistException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EmailAlreadyExistException(Throwable throwable) {
        super(throwable);
    }

    public EmailAlreadyExistException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
