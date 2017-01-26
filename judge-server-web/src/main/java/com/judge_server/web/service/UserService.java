package com.judge_server.web.service;

import com.judge_server.core.entity.user.User;
import com.judge_server.core.entity.judge.Submit;
import com.judge_server.web.resource.user.UserResource;
import com.judge_server.web.resource.user.UserSubmitResource;
import org.springframework.data.domain.Page;

public interface UserService {

    User getUser(UserResource userResource);
    Page<Submit> getUserSubmit(UserSubmitResource userSubmitResource);

}
