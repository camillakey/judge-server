package com.judge_server.web.component.impl;

import com.judge_server.core.entity.user.User;
import com.judge_server.web.component.UserComponent;
import com.judge_server.web.details.SignInUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserComponentImpl implements UserComponent {

    @Override
    public User signInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SignInUserDetails signInUserDetails = (SignInUserDetails) authentication.getPrincipal();
        return signInUserDetails.getUser();
    }

}
