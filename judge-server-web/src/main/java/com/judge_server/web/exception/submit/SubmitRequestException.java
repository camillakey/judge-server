package com.judge_server.web.exception.submit;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SubmitRequestException extends RuntimeException {
    public SubmitRequestException() {
    }

    public SubmitRequestException(String s) {
        super(s);
    }

    public SubmitRequestException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SubmitRequestException(Throwable throwable) {
        super(throwable);
    }

    public SubmitRequestException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
