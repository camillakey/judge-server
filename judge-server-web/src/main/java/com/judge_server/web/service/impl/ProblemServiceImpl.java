package com.judge_server.web.service.impl;

import com.judge_server.core.entity.judge.Problem;
import com.judge_server.core.repository.judge.ProblemRepository;
import com.judge_server.web.exception.problem.ProblemNotFoundException;
import com.judge_server.web.resource.problem.ProblemListResource;
import com.judge_server.web.resource.problem.ProblemResource;
import com.judge_server.web.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Problem getProblem(ProblemResource problemResource) {
        Problem problem = problemRepository.findByProblemId(problemResource.getProblemId());
        if (problem == null) {
            throw new ProblemNotFoundException();
        }

        return problem;
    }

    @Override
    public Page<Problem> getProblemList(ProblemListResource problemListResource) {
        PageRequest pageRequest = new PageRequest(problemListResource.getPage(), problemListResource.getSize());
        return problemRepository.findAllByOrderByProblemIdAsc(pageRequest);
    }
}
