package com.judge_server.web.service.impl;

import com.judge_server.core.entity.judge.Problem;
import com.judge_server.core.entity.judge.simple.SimpleJudgeIO;
import com.judge_server.core.repository.judge.ProblemRepository;
import com.judge_server.core.repository.judge.simple.SimpleJudgeIORepository;
import com.judge_server.web.exception.problem.create.ProblemIdAlreadyExistException;
import com.judge_server.web.exception.problem.create.SimpleJudgeIOMismatchException;
import com.judge_server.web.resource.problem.create.CreateProblemResource;
import com.judge_server.web.resource.problem.create.CreateSimpleProblemResource;
import com.judge_server.web.service.CreateProblemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProblemServiceImpl implements CreateProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private SimpleJudgeIORepository simpleJudgeIORepository;

    @Override
    public void create(CreateSimpleProblemResource createSimpleProblemResource) {
        if (createSimpleProblemResource.getInput().size() != createSimpleProblemResource.getOutput().size()) {
            throw new SimpleJudgeIOMismatchException();
        }

        Problem problem = saveProblem(createSimpleProblemResource);

        for (int i = 0; i < createSimpleProblemResource.getInput().size(); i++) {
            SimpleJudgeIO simpleJudgeIO = new SimpleJudgeIO();
            simpleJudgeIO.setProblem(problem);
            simpleJudgeIO.setInput(formatIO(createSimpleProblemResource.getInput().get(i)));
            simpleJudgeIO.setOutput(formatIO(createSimpleProblemResource.getOutput().get(i)));

            simpleJudgeIORepository.save(simpleJudgeIO);
        }
    }


    private Problem saveProblem(CreateProblemResource createProblemResource) {
        if (problemRepository.findByProblemId(createProblemResource.getProblemId()) != null) {
            throw new ProblemIdAlreadyExistException();
        }

        Problem problem = new Problem();
        BeanUtils.copyProperties(createProblemResource, problem);
        return problemRepository.save(problem);
    }

    private String formatIO(String str) {
        return str.replace("\r\n", "\n") + "\n";
    }
}
