package com.judge_server.web.controller;

import com.judge_server.web.resource.problem.create.CreateSimpleProblemResource;
import com.judge_server.web.service.CreateProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreateProblemController {

    @Autowired
    private CreateProblemService createProblemService;

    @RequestMapping(path = "/problem/create", method = RequestMethod.GET)
    public String getProblemCreate(Model model) {
        return "/problem/create/index";
    }

    @RequestMapping(path = "/problem/create/simple", method = RequestMethod.GET)
    public String getSimpleProblemCreate(Model model) {
        return "/problem/create/simple";
    }

    @RequestMapping(path = "/problem/create/simple", method = RequestMethod.POST)
    public String postSimpleProblemCreate(@Validated CreateSimpleProblemResource resource, Model model) {
        createProblemService.create(resource);
        return "redirect:/problem/" + resource.getProblemId();
    }

}
