package com.judge_server.web.controller;

import com.judge_server.core.entity.judge.JudgeSystem;
import com.judge_server.core.entity.judge.Problem;
import com.judge_server.core.entity.user.Role;
import com.judge_server.core.entity.user.User;
import com.judge_server.core.repository.judge.ProblemRepository;
import com.judge_server.core.repository.user.UserRepository;
import com.judge_server.web.details.SignInUserDetails;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

public abstract class AbstractControllerTest {

    private static boolean needSeedUser = true;
    private static boolean needSeedAdmin = true;
    private static boolean needSeedProblem = true;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void seedUser() {
        if (needSeedUser) {
            List<Role> roleList = new ArrayList<>();
            roleList.add(Role.User);

            User user = new User();
            user.setEmail("AbstractControllerTest@example.com");
            user.setUsername("AbstractControllerTest");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole(roleList);
            userRepository.save(user);

            needSeedUser = false;
        }
    }

    @Before
    public void seedAdmin() {
        if (needSeedAdmin) {
            List<Role> roleList = new ArrayList<>();
            roleList.add(Role.User);
            roleList.add(Role.Admin);

            User admin = new User();
            admin.setEmail("AbstractControllerTestAdmin@example.com");
            admin.setUsername("AbstractControllerTestAdmin");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setRole(roleList);
            userRepository.save(admin);

            needSeedAdmin = false;
        }
    }

    @Before
    public void seedProblem() {
        if (needSeedProblem) {
            for (int i = 0; i < 99; i++) {
                Problem problem = new Problem();
                problem.setProblemId("problem-" + i);
                problem.setJudgeSystem(JudgeSystem.Simple);
                problem.setTitle("title-" + i);
                problem.setDetail("detail-" + i);
                problem.setTimeLimit(1000L);
                problem.setMemoryLimit(65536L);
                problemRepository.save(problem);
            }

            needSeedProblem = false;
        }
    }


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .defaultRequest(get("/").with(csrf()))
                .addFilters(springSecurityFilterChain)
                .build();
    }

    MockMvc mockMvc() {
        return mockMvc;
    }

    RequestPostProcessor signIn() {
        User user = userRepository.findByUsername("AbstractControllerTest");
        return user(new SignInUserDetails(user));
    }

    RequestPostProcessor signInAsAdmin() {
        User user = userRepository.findByUsername("AbstractControllerTestAdmin");
        return user(new SignInUserDetails(user));
    }
}
