package com.judge_server.runner.component;

import com.judge_server.runner.dto.RunnerRequest;
import com.judge_server.runner.dto.RunnerResult;
import com.judge_server.runner.exception.RunnerException;

/**
 * Runner.
 */
public interface Runner {
    /**
     * Runs request.
     * @param runnerRequest request.
     * @return result.
     * @throws RunnerException
     */
    RunnerResult run(RunnerRequest runnerRequest) throws RunnerException;
}
