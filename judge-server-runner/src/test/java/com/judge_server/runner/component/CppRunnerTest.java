package com.judge_server.runner.component;

import com.judge_server.runner.dto.RunnerRequest;
import com.judge_server.runner.dto.RunnerResult;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CppRunnerTest extends AbstractRunnerTest {

    @Autowired
    private CppRunner cppRunner;

    @Override
    RunnerRequest.Language language() {
        return RunnerRequest.Language.Cpp;
    }

    @Override
    RunnerResult run(RunnerRequest runnerRequest) {
        return cppRunner.run(runnerRequest);
    }

}
