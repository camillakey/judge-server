package com.judge_server.web.resource.problem;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ProblemResource {

    @NotBlank
    private String problemId;
}
