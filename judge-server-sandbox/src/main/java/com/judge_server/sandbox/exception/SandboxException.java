package com.judge_server.sandbox.exception;

/**
 * Thrown to indicate that a sandbox has been crashed.
 */
public class SandboxException extends RuntimeException {
    public SandboxException() {
        super();
    }

    public SandboxException(String message) {
        super(message);
    }

    public SandboxException(String message, Throwable cause) {
        super(message, cause);
    }

    public SandboxException(Throwable cause) {
        super(cause);
    }

    public SandboxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
