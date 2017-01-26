package com.judge_server.runner.component.impl;

import com.judge_server.runner.component.Runner;
import com.judge_server.runner.dto.RunnerRequest;
import com.judge_server.runner.dto.RunnerResult;
import com.judge_server.runner.exception.RunnerException;
import com.judge_server.sandbox.component.SandboxComponent;
import com.judge_server.sandbox.core.Sandbox;
import com.judge_server.sandbox.dto.SandboxEnvironment;
import com.judge_server.sandbox.dto.SandboxResult;
import com.judge_server.sandbox.exception.SandboxException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Abstract runner class that supports to implements runner.
 */
public abstract class AbstractRunnerImpl implements Runner {

    @Autowired
    private SandboxComponent sandboxComponent;

    @Override
    public RunnerResult run(RunnerRequest runnerRequest) throws RunnerException {
        SandboxEnvironment sandboxEnvironment =
                new SandboxEnvironment(runnerRequest.getTimeLimit(), runnerRequest.getMemoryLimit());

        String target = getTarget(runnerRequest.getSource());
        String extension = getExtension();
        String compileCommand = getCompileCommand(target);
        String executeCommand = getExecuteCommand(target);

        try (Sandbox sandbox = sandboxComponent.create(sandboxEnvironment)) {
            sandbox.run("echo \"" + runnerRequest.getSource().replace("\"", "\\\"") + "\" > " + target + extension);

            SandboxResult compileResult = sandbox.run(compileCommand);
            SandboxResult executeResult =
                    compileResult.getExitValue() == 0 ? sandbox.run(executeCommand, runnerRequest.getStdin()) : null;

            return executeResult == null ?
                    createCompileErrorResult(compileResult) : createExecuteResult(compileResult, executeResult);
        } catch (SandboxException e) {
            throw new RunnerException(e);
        }
    }

    /**
     * Creates compile error result.
     * @param compileResult compile result.
     * @return compile error result.
     */
    private RunnerResult createCompileErrorResult(SandboxResult compileResult) {
        return new RunnerResult(
                RunnerResult.State.CompileError,
                "",
                "",
                compileResult.getStderr(),
                compileResult.getUsedTime(),
                compileResult.isTimeLimited(),
                compileResult.getUsedMemory(),
                compileResult.isMemoryLimited()
        );
    }

    /**
     * Creates execute result.
     * @param compileResult compile result.
     * @param executeResult execute result.
     * @return execute result.
     */
    private RunnerResult createExecuteResult(SandboxResult compileResult, SandboxResult executeResult) {
        RunnerResult.State state = RunnerResult.State.Success;

        if (executeResult.getExitValue() != 0) {
            state = RunnerResult.State.RuntimeError;
        }

        if (executeResult.isTimeLimited()) {
            state = RunnerResult.State.TimeLimited;
        } else if (executeResult.isMemoryLimited()) {
            state = RunnerResult.State.MemoryLimited;
        }

        return new RunnerResult(
                state,
                executeResult.getStdout(),
                executeResult.getStderr(),
                compileResult.getStdout(),
                executeResult.getUsedTime(),
                executeResult.isTimeLimited(),
                executeResult.getUsedMemory(),
                executeResult.isMemoryLimited()
        );
    }


    /**
     * Returns target name.
     * @param source source code.
     * @return
     */
    String getTarget(String source) {
        return UUID.randomUUID().toString();
    }

    /**
     * Returns extension of each language.
     * @return
     */
    abstract String getExtension();

    /**
     * Returns compile command.
     * @param target target name.
     * @return
     */
    abstract String getCompileCommand(String target);

    /**
     * Returns execution command.
     * @param target target name.
     * @return
     */
    abstract String getExecuteCommand(String target);
}
