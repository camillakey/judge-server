package com.judge_server.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sandbox execution environment.
 * This params work in each commands.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SandboxEnvironment {

    /**
     * Time limit in milliseconds.
     */
    private long timeLimit;

    /**
     * Memory limit in kilobytes.
     */
    private long memoryLimit;

}
