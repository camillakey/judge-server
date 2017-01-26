package com.judge_server.web.exception.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExistException extends RuntimeException {
    public UsernameAlreadyExistException() {
    }

    public UsernameAlreadyExistException(String s) {
        super(s);
    }

    public UsernameAlreadyExistException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UsernameAlreadyExistException(Throwable throwable) {
        super(throwable);
    }

    public UsernameAlreadyExistException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
