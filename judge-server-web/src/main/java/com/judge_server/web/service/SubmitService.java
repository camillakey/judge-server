package com.judge_server.web.service;

import com.judge_server.core.entity.judge.Submit;
import com.judge_server.web.resource.submit.SubmitListResource;
import com.judge_server.web.resource.submit.SubmitResource;
import org.springframework.data.domain.Page;

public interface SubmitService {

    Page<Submit> getSubmitList(SubmitListResource submitListResource);
    void submit(SubmitResource submitResource);

}
