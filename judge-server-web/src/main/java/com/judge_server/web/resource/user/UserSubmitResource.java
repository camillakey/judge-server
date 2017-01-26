package com.judge_server.web.resource.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

@Data
public class UserSubmitResource {

    @NotBlank
    private String username;

    @Min(0)
    private int page = 0;

    @Min(1)
    private int size = 10;
}
