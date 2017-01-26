package com.judge_server.web.controller;

import com.judge_server.web.resource.authentication.SignUpResource;
import com.judge_server.web.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(path = "/signIn", method = RequestMethod.GET)
    public String getSignIn(Model model) {
        return "/authentication/signIn";
    }

    @RequestMapping(path = "/signUp", method = RequestMethod.GET)
    public String getSignUp(Model model) {
        return "/authentication/signUp";
    }

    @RequestMapping(path = "/signUp", method = RequestMethod.POST)
    public String postSignUp(@Validated SignUpResource resource, Model model) {
        authenticationService.signUp(resource);
        return "redirect:/";
    }

}
