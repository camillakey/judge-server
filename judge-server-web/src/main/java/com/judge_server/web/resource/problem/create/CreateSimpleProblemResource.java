package com.judge_server.web.resource.problem.create;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateSimpleProblemResource extends CreateProblemResource {

    @NotNull
    private List<String> input;

    @NotNull
    private List<String> output;

}
