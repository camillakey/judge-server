package com.judge_server.core.repository.user;

import com.judge_server.core.entity.user.User;
import com.judge_server.core.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractRepository<User> {
    User findByEmail(String email);
    User findByUsername(String username);
}
