package com.judge_server.web.exception.problem.create;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProblemIdAlreadyExistException extends RuntimeException {
    public ProblemIdAlreadyExistException() {
    }

    public ProblemIdAlreadyExistException(String s) {
        super(s);
    }

    public ProblemIdAlreadyExistException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ProblemIdAlreadyExistException(Throwable throwable) {
        super(throwable);
    }

    public ProblemIdAlreadyExistException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
