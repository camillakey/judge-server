package com.judge_server.runner.component.impl;

import com.judge_server.runner.component.CRunner;
import com.judge_server.runner.component.CppRunner;
import com.judge_server.runner.component.JavaRunner;
import com.judge_server.runner.component.Runner;
import com.judge_server.runner.dto.RunnerRequest;
import com.judge_server.runner.dto.RunnerResult;
import com.judge_server.runner.exception.RunnerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RunnerImpl implements Runner {

    @Autowired
    private CRunner cRunner;

    @Autowired
    private CppRunner cppRunner;

    @Autowired
    private JavaRunner javaRunner;

    @Override
    public RunnerResult run(RunnerRequest runnerRequest) throws RunnerException {
        switch (runnerRequest.getLanguage()) {
            case C:
                return cRunner.run(runnerRequest);
            case Cpp:
                return cppRunner.run(runnerRequest);
            case Java:
                return javaRunner.run(runnerRequest);
            default:
                throw new RunnerException();
        }
    }
}
