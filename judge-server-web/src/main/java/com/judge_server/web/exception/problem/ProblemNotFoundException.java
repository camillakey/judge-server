package com.judge_server.web.exception.problem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProblemNotFoundException extends RuntimeException {
    public ProblemNotFoundException() {
    }

    public ProblemNotFoundException(String s) {
        super(s);
    }

    public ProblemNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ProblemNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public ProblemNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
