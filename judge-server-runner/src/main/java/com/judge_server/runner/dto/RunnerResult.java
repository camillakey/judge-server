package com.judge_server.runner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Runner result.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RunnerResult {

    /**
     * State.
     */
    private State state;

    /**
     * Standard output.
     */
    private String stdout;

    /**
     * Standard error.
     */
    private String stderr;

    /**
     * Compile message.
     */
    private String compileMessage;

    /**
     * Used time in milliseconds.
     */
    private long usedTime;

    /**
     * true if time limited.
     */
    private boolean timeLimited;

    /**
     * Used memory in kilobytes.
     */
    private long usedMemory;

    /**
     * true if memory limited.
     */
    private boolean memoryLimited;


    /**
     * State.
     */
    public enum State {
        CompileError,
        RuntimeError,
        TimeLimited,
        MemoryLimited,
        Success,
    }
}
