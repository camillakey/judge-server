package com.judge_server.web.resource.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserResource {

    @NotBlank
    private String username;

}
