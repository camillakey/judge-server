package com.judge_server.judge.exception;

/**
 * Thrown to indicate that a judge system crashed.
 */
public class JudgeException extends RuntimeException {
    public JudgeException() {
    }

    public JudgeException(String s) {
        super(s);
    }

    public JudgeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public JudgeException(Throwable throwable) {
        super(throwable);
    }

    public JudgeException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
