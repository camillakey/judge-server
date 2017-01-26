package com.judge_server.web.integrated;

import com.judge_server.core.entity.user.Role;
import com.judge_server.core.entity.user.User;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public abstract class AbstractIntegratedTest {

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    MockMvc mockMvc() {
        return mockMvc;
    }

    RequestPostProcessor signIn() {
        User user = userRepository.findByUsername("AbstractIntegratedTest");

        if (user == null) {
            user = signUp("AbstractIntegratedTest", Collections.singletonList(Role.User));
        }

        return user(new SignInUserDetails(user));
    }

    RequestPostProcessor signInAsAdmin() {
        User user = userRepository.findByUsername("AbstractIntegratedTestAdmin");

        if (user == null) {
            user = signUp("AbstractIntegratedTestAdmin", Arrays.asList(Role.Admin, Role.User));
        }

        return user(new SignInUserDetails(user));
    }

    private User signUp(String username, List<Role> roleList) {
        User user = new User();
        user.setEmail(username + "@example.com");
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode("password"));
        user.setRole(roleList);
        return userRepository.save(user);
    }


}
