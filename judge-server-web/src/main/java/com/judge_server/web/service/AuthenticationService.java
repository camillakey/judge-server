package com.judge_server.web.service;

import com.judge_server.web.resource.authentication.SignUpResource;

public interface AuthenticationService {
    void signUp(SignUpResource signUpResource);
}
