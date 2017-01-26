package com.judge_server.web.service.impl;

import com.judge_server.core.entity.user.User;
import com.judge_server.core.repository.user.UserRepository;
import com.judge_server.web.details.SignInUserDetails;
import com.judge_server.web.service.SignInUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SignInUserDetailsServiceImpl implements SignInUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }

        return new SignInUserDetails(user);
    }

}
