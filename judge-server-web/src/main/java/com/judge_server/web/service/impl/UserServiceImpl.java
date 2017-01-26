package com.judge_server.web.service.impl;

import com.judge_server.core.entity.user.User;
import com.judge_server.core.entity.judge.Submit;
import com.judge_server.core.repository.judge.SubmitRepository;
import com.judge_server.core.repository.user.UserRepository;
import com.judge_server.web.exception.user.UserNotFoundException;
import com.judge_server.web.resource.user.UserResource;
import com.judge_server.web.resource.user.UserSubmitResource;
import com.judge_server.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubmitRepository submitRepository;

    @Override
    public User getUser(UserResource userResource) {
        User user = userRepository.findByUsername(userResource.getUsername());
        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    @Override
    public Page<Submit> getUserSubmit(UserSubmitResource userSubmitResource) {
        User user = userRepository.findByUsername(userSubmitResource.getUsername());
        if (user == null) {
            throw new UserNotFoundException();
        }

        PageRequest pageRequest = new PageRequest(userSubmitResource.getPage(), userSubmitResource.getSize());
        return submitRepository.findByUserOrderByCreatedDesc(user, pageRequest);
    }
}
