package com.judge_server.web.controller;

import com.judge_server.web.resource.submit.SubmitListResource;
import com.judge_server.web.resource.submit.SubmitResource;
import com.judge_server.web.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SubmitController {

    @Autowired
    private SubmitService submitService;

    @RequestMapping(path = {
            "/submit",
            "/submit/{problemId}",
    }, method = RequestMethod.GET)
    public String getSubmitList(@Validated SubmitListResource resource, Model model) {
        model.addAttribute("submitList", submitService.getSubmitList(resource));
        return "/submit/list";
    }

    @RequestMapping(path = "/submit/{problemId}", method = RequestMethod.POST)
    public String postSubmit(@Validated SubmitResource resource, Model model) {
        submitService.submit(resource);
        return "redirect:/submit";
    }

}
