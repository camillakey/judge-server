package com.judge_server.web.service.impl;

import com.judge_server.core.entity.user.Role;
import com.judge_server.core.entity.user.User;
import com.judge_server.core.repository.user.UserRepository;
import com.judge_server.web.exception.authentication.EmailAlreadyExistException;
import com.judge_server.web.exception.authentication.UsernameAlreadyExistException;
import com.judge_server.web.resource.authentication.SignUpResource;
import com.judge_server.web.service.AuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpResource signUpResource) {
        if (userRepository.findByEmail(signUpResource.getEmail()) != null) {
            throw new UsernameAlreadyExistException();
        } else if (userRepository.findByUsername(signUpResource.getUsername()) != null) {
            throw new EmailAlreadyExistException();
        }

        List<Role> roleList = new ArrayList<>();
        roleList.add(Role.User);

        User user = new User();
        BeanUtils.copyProperties(signUpResource, user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleList);
        userRepository.save(user);
    }
}
