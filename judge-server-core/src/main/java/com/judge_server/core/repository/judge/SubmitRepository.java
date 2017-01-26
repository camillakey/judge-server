package com.judge_server.core.repository.judge;

import com.judge_server.core.entity.user.User;
import com.judge_server.core.entity.judge.Language;
import com.judge_server.core.entity.judge.Problem;
import com.judge_server.core.entity.judge.Submit;
import com.judge_server.core.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmitRepository extends AbstractRepository<Submit> {
    Page<Submit> findByUserOrderByCreatedDesc(User user, Pageable pageable);
    Page<Submit> findByProblemOrderByCreatedDesc(Problem problem, Pageable pageable);
    Page<Submit> findByLanguageOrderByCreatedDesc(Language language, Pageable pageable);
}
