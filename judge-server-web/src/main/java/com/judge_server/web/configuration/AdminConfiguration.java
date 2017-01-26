package com.judge_server.web.configuration;

import com.judge_server.core.entity.user.Role;
import com.judge_server.core.entity.user.User;
import com.judge_server.core.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class AdminConfiguration {

    @Autowired
    private AdminConfigurationProperties adminConfigurationProperties;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void adminConfiguration() {
        if (userRepository.findByEmail(adminConfigurationProperties.getEmail()) == null) {
            List<Role> role = new ArrayList<>();
            role.add(Role.User);
            role.add(Role.Admin);

            User user = new User();
            user.setEmail(adminConfigurationProperties.getEmail());
            user.setUsername(adminConfigurationProperties.getUsername());
            user.setPassword(passwordEncoder.encode(adminConfigurationProperties.getPassword()));
            user.setRole(role);
            userRepository.save(user);
        }
    }
}
