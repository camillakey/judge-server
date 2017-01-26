package com.judge_server.runner.component;

import com.judge_server.runner.dto.RunnerRequest;
import com.judge_server.runner.dto.RunnerResult;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaRunnerTest extends AbstractRunnerTest {

    @Autowired
    private JavaRunner javaRunner;

    @Override
    RunnerRequest.Language language() {
        return RunnerRequest.Language.Java;
    }

    @Override
    RunnerResult run(RunnerRequest runnerRequest) {
        return javaRunner.run(runnerRequest);
    }

    @Override
    long defaultTimeLimit() {
        return 5000L;
    }

}
