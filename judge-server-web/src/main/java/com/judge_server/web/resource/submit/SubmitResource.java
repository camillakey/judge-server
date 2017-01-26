package com.judge_server.web.resource.submit;

import com.judge_server.core.entity.judge.Language;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class SubmitResource {

    @NotBlank
    private String problemId;

    @NotNull
    private Language language;

    @NotNull
    private String source;

}
