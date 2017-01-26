package com.judge_server.web.controller;

import com.judge_server.web.resource.problem.ProblemListResource;
import com.judge_server.web.resource.problem.ProblemResource;
import com.judge_server.web.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @RequestMapping(path = "/problem", method = RequestMethod.GET)
    public String getProblemList(@Validated ProblemListResource resource, Model model) {
        model.addAttribute("problemList", problemService.getProblemList(resource));
        return "/problem/list";
    }

    @RequestMapping(path = "/problem/{problemId}", method = RequestMethod.GET)
    public String getProblem(@Validated ProblemResource resource, Model model) {
        model.addAttribute("problem", problemService.getProblem(resource));
        return "/problem/index";
    }

}
