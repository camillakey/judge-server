package com.judge_server.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest extends AbstractControllerTest  {

    @Test
    public void getUser() throws Exception {
        mockMvc().perform(post("/signUp")
                .param("email", "getUser@example.com")
                .param("username", "getUser")
                .param("password", "password")
                .param("passwordConfirm", "password"))
                .andExpect(redirectedUrl("/"));

        mockMvc().perform(get("/getUser").with(signIn()))
                .andExpect(status().isOk())
                .andExpect(view().name("/user/index"));
    }

    @Test
    public void getUserNotFound() throws Exception {
        mockMvc().perform(get("/getUserNotFound").with(signIn()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getUserSubmit() throws Exception {
        mockMvc().perform(post("/signUp")
                .param("email", "getUserSubmit@example.com")
                .param("username", "getUserSubmit")
                .param("password", "password")
                .param("passwordConfirm", "password"))
                .andExpect(redirectedUrl("/"));

        mockMvc().perform(get("/getUserSubmit/submit").with(signIn()))
                .andExpect(status().isOk())
                .andExpect(view().name("/user/submit"));
    }

    @Test
    public void getUserSubmitUserNotFound() throws Exception {
        mockMvc().perform(get("/getUserSubmitUserNotFound/submit").with(signIn()))
                .andExpect(status().isNotFound());
    }

}
