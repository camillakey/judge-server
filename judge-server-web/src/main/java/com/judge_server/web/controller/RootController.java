package com.judge_server.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getRoot(Model model) {
        return "/root/index";
    }

}
