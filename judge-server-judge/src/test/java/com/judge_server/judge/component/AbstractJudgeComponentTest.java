package com.judge_server.judge.component;

import com.judge_server.core.entity.judge.*;
import com.judge_server.core.entity.user.Role;
import com.judge_server.core.entity.user.User;
import com.judge_server.core.repository.judge.ProblemRepository;
import com.judge_server.core.repository.judge.SubmitRepository;
import com.judge_server.core.repository.user.UserRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractJudgeComponentTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private SubmitRepository submitRepository;

    private User user;
    private Problem problem;

    @Before
    public void before() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(Role.User);

        user = new User();
        user.setEmail(UUID.randomUUID().toString() + "@example.com");
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword("password");
        user.setRole(roleList);
        userRepository.save(user);

        problem = new Problem();
        problem.setProblemId(UUID.randomUUID().toString());
        problem.setJudgeSystem(JudgeSystem.Simple);
        problem.setTitle("title");
        problem.setDetail("detail");
        problem.setTimeLimit(1000L);
        problem.setMemoryLimit(65536L);
        problemRepository.save(problem);
    }

    Problem problem() {
        return problem;
    }

    Submit submit(Language language, String source) {
        Submit submit = new Submit();
        submit.setUser(user);
        submit.setProblem(problem);
        submit.setLanguage(language);
        submit.setSubmitState(SubmitState.Waiting);
        submit.setSource(source);
        return submitRepository.save(submit);
    }

}
