package com.judge_server.judge.component.impl;

import com.judge_server.core.entity.judge.Submit;
import com.judge_server.judge.component.JudgeComponent;
import com.judge_server.judge.component.SimpleJudgeComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Primary
public class JudgeComponentImpl implements JudgeComponent {

    @Autowired
    private SimpleJudgeComponent simpleJudgeComponent;

    @Async
    public void judge(Submit submit) {
        switch (submit.getProblem().getJudgeSystem()) {
            case Simple:
                simpleJudgeComponent.judge(submit);
                break;
        }
    }

}
