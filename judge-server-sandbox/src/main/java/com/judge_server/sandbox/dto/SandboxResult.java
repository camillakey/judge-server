package com.judge_server.sandbox.dto;

import lombok.Data;

/**
 * Sandbox execution result.
 */
@Data
public class SandboxResult {

    /**
     * Exit value.
     */
    private int exitValue;

    /**
     * Standard output.
     */
    private String stdout;

    /**
     * Standard error.
     */
    private String stderr;

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

    public SandboxResult() {
    }

    public SandboxResult(int exitValue, String stdout) {
        this.exitValue = exitValue;
        this.stdout = stdout;
    }

    public SandboxResult(int exitValue, String stdout, String stderr) {
        this.exitValue = exitValue;
        this.stdout = stdout;
        this.stderr = stderr;
    }

}
