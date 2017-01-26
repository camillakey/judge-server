package com.judge_server.web.service.impl;

import com.judge_server.core.entity.judge.Problem;
import com.judge_server.core.entity.judge.Submit;
import com.judge_server.core.entity.judge.SubmitState;
import com.judge_server.core.repository.judge.ProblemRepository;
import com.judge_server.core.repository.judge.SubmitRepository;
import com.judge_server.judge.component.JudgeComponent;
import com.judge_server.web.component.UserComponent;
import com.judge_server.web.exception.problem.ProblemNotFoundException;
import com.judge_server.web.exception.submit.SubmitRequestException;
import com.judge_server.web.resource.submit.SubmitListResource;
import com.judge_server.web.resource.submit.SubmitResource;
import com.judge_server.web.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SubmitServiceImpl implements SubmitService {

    @Autowired
    private JudgeComponent judgeComponent;

    @Autowired
    private UserComponent userComponent;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private SubmitRepository submitRepository;

    @Override
    public Page<Submit> getSubmitList(SubmitListResource submitListResource) {
        PageRequest pageRequest = new PageRequest(submitListResource.getPage(), submitListResource.getSize());

        if (submitListResource.getProblemId() != null) {
            Problem problem = problemRepository.findByProblemId(submitListResource.getProblemId());
            if (problem == null) {
                throw new ProblemNotFoundException();
            }

            return submitRepository.findByProblemOrderByCreatedDesc(problem, pageRequest);
        } else {
            return submitRepository.findAllByOrderByCreatedDesc(pageRequest);
        }
    }

    @Override
    public void submit(SubmitResource submitResource) {
        Submit submit = new Submit();
        submit.setUser(userComponent.signInUser());
        submit.setProblem(problemRepository.findByProblemId(submitResource.getProblemId()));
        submit.setLanguage(submitResource.getLanguage());
        submit.setSubmitState(SubmitState.Waiting);
        submit.setSource(submitResource.getSource());

        if (submit.getProblem() == null) {
            throw new SubmitRequestException();
        }

        submitRepository.save(submit);

        judgeComponent.judge(submit);
    }
}
