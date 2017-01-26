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
public class AuthenticationControllerTest extends AbstractControllerTest {

    @Test
    public void getSignIn() throws Exception {
        mockMvc().perform(get("/signIn"))
                .andExpect(status().isOk())
                .andExpect(view().name("/authentication/signIn"));
    }

    @Test
    public void getSignUp() throws Exception {
        mockMvc().perform(get("/signUp"))
                .andExpect(status().isOk())
                .andExpect(view().name("/authentication/signUp"));
    }

    @Test
    public void postSignUp() throws Exception {
        mockMvc().perform(post("/signUp")
                .param("email", "postSignUp@example.com")
                .param("username", "postSignUp")
                .param("password", "password")
                .param("passwordConfirm", "password"))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void postSignUpEmailMissing() throws Exception {
        mockMvc().perform(post("/signUp")
                .param("username", "postSignUpEmailMissing")
                .param("password", "password")
                .param("passwordConfirm", "password"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postSignUpUsernameMissing() throws Exception {
        mockMvc().perform(post("/signUp")
                .param("email", "postSignUpUsernameMissing@example.com")
                .param("password", "password")
                .param("passwordConfirm", "password"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postSignUpPasswordMissing() throws Exception {
        mockMvc().perform(post("/signUp")
                .param("email", "postSignUpPasswordMissing@example.com")
                .param("username", "postSignUpPasswordMissing")
                .param("passwordConfirm", "password"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postSignUpPasswordConfirmMissing() throws Exception {
        mockMvc().perform(post("/signUp")
                .param("email", "postSignUpPasswordConfirmMissing@example.com")
                .param("username", "postSignUpPasswordConfirmMissing")
                .param("password", "password"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postSignUpExistEmail() throws Exception {
        mockMvc().perform(post("/signUp")
                .param("email", "postSignUpExistEmail@example.com")
                .param("username", "postSignUpExistEmail")
                .param("password", "password")
                .param("passwordConfirm", "password"))
                .andExpect(redirectedUrl("/"));

        mockMvc().perform(post("/signUp")
                .param("email", "postSignUpExistEmail@example.com")
                .param("username", "postSignUpExistEmail2")
                .param("password", "password")
                .param("passwordConfirm", "password"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postSignUpExistUsername() throws Exception {
        mockMvc().perform(post("/signUp")
                .param("email", "postSignUpExistUsername@example.com")
                .param("username", "postSignUpExistUsername")
                .param("password", "password")
                .param("passwordConfirm", "password"))
                .andExpect(redirectedUrl("/"));

        mockMvc().perform(post("/signUp")
                .param("email", "postSignUpExistUsername2@example.com")
                .param("username", "postSignUpExistUsername")
                .param("password", "password")
                .param("passwordConfirm", "password"))
                .andExpect(status().isBadRequest());
    }
}
