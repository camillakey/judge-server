package com.judge_server.web.resource.problem;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;

@Data
public class ProblemListResource {

    @Min(0)
    private int page = 0;

    @Min(1)
    private int size = 10;

}
