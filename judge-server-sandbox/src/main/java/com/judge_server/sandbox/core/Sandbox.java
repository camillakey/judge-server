package com.judge_server.sandbox.core;

import com.judge_server.sandbox.dto.SandboxResult;
import com.judge_server.sandbox.exception.SandboxException;

/**
 * Sandbox.
 */
public interface Sandbox extends AutoCloseable {

    /**
     * Runs command.
     * @param command command.
     * @return execution result.
     * @throws SandboxException
     */
    SandboxResult run(String command) throws SandboxException;

    /**
     * Runs command with stdin.
     * @param command command.
     * @param stdin standard input.
     * @return execution result.
     * @throws SandboxException
     */
    SandboxResult run(String command, String stdin) throws SandboxException;

    void close() throws SandboxException;
}
