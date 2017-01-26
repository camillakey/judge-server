package com.judge_server.core.repository.judge;

import com.judge_server.core.entity.judge.Problem;
import com.judge_server.core.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface  ProblemRepository extends AbstractRepository<Problem> {
    Problem findByProblemId(String problemId);

    Page<Problem> findAllByOrderByProblemIdAsc(Pageable pageable);
    Page<Problem> findAllByOrderByProblemIdDesc(Pageable pageable);
}
