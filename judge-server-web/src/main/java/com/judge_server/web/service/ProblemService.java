package com.judge_server.web.service;

import com.judge_server.core.entity.judge.Problem;
import com.judge_server.web.resource.problem.ProblemListResource;
import com.judge_server.web.resource.problem.ProblemResource;
import org.springframework.data.domain.Page;

public interface ProblemService {

    Problem getProblem(ProblemResource problemResource);
    Page<Problem> getProblemList(ProblemListResource problemListResource);

}
