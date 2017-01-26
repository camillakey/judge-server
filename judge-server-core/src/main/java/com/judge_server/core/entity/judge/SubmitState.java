package com.judge_server.core.entity.judge;

public enum SubmitState {
    JudgeError,
    Waiting,
    CompileError,
    Running,
    RuntimeError,
    MemoryLimited,
    TimeLimited,
    Rejected,
    Accepted;
}
