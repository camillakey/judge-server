package com.judge_server.judge.component.impl;

import com.judge_server.core.entity.judge.Problem;
import com.judge_server.core.entity.judge.Submit;
import com.judge_server.core.entity.judge.SubmitState;
import com.judge_server.core.entity.judge.simple.SimpleJudgeIO;
import com.judge_server.core.repository.judge.SubmitRepository;
import com.judge_server.core.repository.judge.simple.SimpleJudgeIORepository;
import com.judge_server.judge.component.SimpleJudgeComponent;
import com.judge_server.judge.converter.LanguageEnumConverter;
import com.judge_server.judge.exception.JudgeException;
import com.judge_server.runner.component.Runner;
import com.judge_server.runner.dto.RunnerRequest;
import com.judge_server.runner.dto.RunnerResult;
import com.judge_server.runner.exception.RunnerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleJudgeComponentImpl implements SimpleJudgeComponent {

    @Autowired
    private SubmitRepository submitRepository;

    @Autowired
    private SimpleJudgeIORepository simpleJudgeIORepository;

    @Autowired
    private Runner runner;

    @Override
    public void judge(Submit submit) {
        Problem problem = submit.getProblem();
        List<SimpleJudgeIO> simpleJudgeIOList = simpleJudgeIORepository.findByProblem(problem);

        changeSubmitState(submit, SubmitState.Running);

        SubmitState judgeResult = SubmitState.Accepted;
        for (SimpleJudgeIO simpleJudgeIO : simpleJudgeIOList) {
            SubmitState result = judge(submit, simpleJudgeIO);
            if (result != SubmitState.Accepted) {
                judgeResult = result;
                break;
            }
        }

        changeSubmitState(submit, judgeResult);
    }

    /**
     * Judges with submit and judge io data.
     * @param submit submit.
     * @param simpleJudgeIO simple judge io.
     * @return judge result.
     */
    private SubmitState judge(Submit submit, SimpleJudgeIO simpleJudgeIO) {
        RunnerRequest request = new RunnerRequest(
                LanguageEnumConverter.convert(submit.getLanguage()),
                submit.getSource(),
                simpleJudgeIO.getInput(),
                submit.getProblem().getTimeLimit(),
                submit.getProblem().getMemoryLimit());

        try {
            RunnerResult result = runner.run(request);

            switch (result.getState()) {
                case CompileError:
                    return SubmitState.CompileError;
                case RuntimeError:
                    return SubmitState.RuntimeError;
                case TimeLimited:
                    return SubmitState.TimeLimited;
                case MemoryLimited:
                    return SubmitState.MemoryLimited;
                case Success:
                    return simpleJudgeIO.getOutput().equals(result.getStdout())
                            ? SubmitState.Accepted : SubmitState.Rejected;
                default:
                    throw new JudgeException("unexpected runner result");
            }
        } catch (RunnerException
                | JudgeException e) {
            return SubmitState.JudgeError;
        }
    }

    /**
     * Changes submit state.
     * @param submit submit.
     * @param submitState submit state.
     */
    private void changeSubmitState(Submit submit, SubmitState submitState) {
        submit.setSubmitState(submitState);
        submitRepository.save(submit);
    }

}
