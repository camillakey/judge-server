package com.judge_server.runner.exception;

/**
 * Thrown to indicate that a runner has been crashed.
 */
public class RunnerException extends RuntimeException {
    public RunnerException() {
    }

    public RunnerException(String message) {
        super(message);
    }

    public RunnerException(String message, Throwable cause) {
        super(message, cause);
    }

    public RunnerException(Throwable cause) {
        super(cause);
    }

    public RunnerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
