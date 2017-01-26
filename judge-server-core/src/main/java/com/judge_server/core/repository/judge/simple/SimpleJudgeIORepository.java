package com.judge_server.core.repository.judge.simple;

import com.judge_server.core.entity.judge.Problem;
import com.judge_server.core.entity.judge.simple.SimpleJudgeIO;
import com.judge_server.core.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimpleJudgeIORepository extends AbstractRepository<SimpleJudgeIO> {
    List<SimpleJudgeIO> findByProblem(Problem problem);
}
