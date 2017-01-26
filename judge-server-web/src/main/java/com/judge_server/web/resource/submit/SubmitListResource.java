package com.judge_server.web.resource.submit;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class SubmitListResource {

    private String problemId;

    @Min(0)
    private int page = 0;

    @Min(1)
    private int size = 10;

}
