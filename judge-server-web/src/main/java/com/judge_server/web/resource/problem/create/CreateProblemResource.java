package com.judge_server.web.resource.problem.create;

import com.judge_server.core.entity.judge.JudgeSystem;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CreateProblemResource {

    @NotBlank
    private String problemId;

    @NotNull
    private JudgeSystem judgeSystem;

    @NotBlank
    private String title;

    @NotBlank
    private String detail;

    @Min(0)
    @Max(20000)
    private long timeLimit = 1000;

    @Min(0)
    @Max(262144)
    private long memoryLimit = 65536;

}
