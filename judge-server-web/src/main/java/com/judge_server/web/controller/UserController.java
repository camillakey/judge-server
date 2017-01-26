package com.judge_server.web.controller;

import com.judge_server.web.resource.user.UserResource;
import com.judge_server.web.resource.user.UserSubmitResource;
import com.judge_server.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    private String getUser(@Validated UserResource userResource, Model model) {
        model.addAttribute("user", userService.getUser(userResource));
        return "/user/index";
    }

    @RequestMapping(path = "/{username}/submit", method = RequestMethod.GET)
    private String getUserSubmit(@Validated UserSubmitResource userSubmitResource, Model model) {
        model.addAttribute("submitList", userService.getUserSubmit(userSubmitResource));
        return "/user/submit";
    }

}
