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
public class SubmitControllerTest extends AbstractControllerTest {

    @Test
    public void getSubmitList() throws Exception {
        mockMvc().perform(get("/submit").with(signIn()))
                .andExpect(status().isOk())
                .andExpect(view().name("/submit/list"));
    }

    @Test
    public void getSubmitListWithProblemId() throws Exception {
        mockMvc().perform(get("/submit/problem-1").with(signIn()))
                .andExpect(status().isOk())
                .andExpect(view().name("/submit/list"));
    }

    @Test
    public void getSubmitListWithProblemIdNotFound() throws Exception {
        mockMvc().perform(get("/submit/problem-100").with(signIn()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postSubmit() throws Exception {
        mockMvc().perform(post("/submit/problem-1").with(signIn())
                .param("language", "Cpp")
                .param("source", "source code"))
                .andExpect(redirectedUrl("/submit"));
    }

    @Test
    public void postSubmitProblemNotFound() throws Exception {
        mockMvc().perform(post("/submit/problem-100").with(signIn())
                .param("language", "Cpp")
                .param("source", "source code"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postSubmitLanguageNotFound() throws Exception {
        mockMvc().perform(post("/submit/problem-1").with(signIn())
                .param("language", "UnknownLanguage")
                .param("source", "source code"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postSubmitProblemIdMissing() throws Exception {
        mockMvc().perform(post("/submit").with(signIn())
                .param("source", "source code")
                .param("language", "Cpp"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void postSubmitSourceMissing() throws Exception {
        mockMvc().perform(post("/submit/problem-1").with(signIn())
                .param("language", "Cpp"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postSubmitLanguageMissing() throws Exception {
        mockMvc().perform(post("/submit/problem-1").with(signIn())
                .param("source", "source code"))
                .andExpect(status().isBadRequest());
    }
}
