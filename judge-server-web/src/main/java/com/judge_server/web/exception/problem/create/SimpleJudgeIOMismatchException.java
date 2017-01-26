package com.judge_server.web.exception.problem.create;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SimpleJudgeIOMismatchException extends RuntimeException {
    public SimpleJudgeIOMismatchException() {
    }

    public SimpleJudgeIOMismatchException(String s) {
        super(s);
    }

    public SimpleJudgeIOMismatchException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SimpleJudgeIOMismatchException(Throwable throwable) {
        super(throwable);
    }

    public SimpleJudgeIOMismatchException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
