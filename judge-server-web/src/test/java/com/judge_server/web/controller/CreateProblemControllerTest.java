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
public class CreateProblemControllerTest extends AbstractControllerTest {

    @Test
    public void getProblemList() throws Exception {
        mockMvc().perform(get("/problem/create").with(signInAsAdmin()))
                .andExpect(status().isOk())
                .andExpect(view().name("/problem/create/index"));
    }

    @Test
    public void createSimpleProblem() throws Exception {
        mockMvc().perform(post("/problem/create/simple").with(signInAsAdmin())
                .param("problemId", "createSimpleProblem")
                .param("judgeSystem", "Simple")
                .param("title", "createSimpleProblemTitle")
                .param("detail", "createSimpleProblemDetail")
                .param("timeLimit", "1000")
                .param("memoryLimit", "65536")
                .param("input", "input")
                .param("output", "output"))
                .andExpect(redirectedUrl("/problem/createSimpleProblem"));
    }

    @Test
    public void createSimpleProblemWithMultiInput() throws Exception {
        mockMvc().perform(post("/problem/create/simple").with(signInAsAdmin())
                .param("problemId", "createSimpleProblemWithMultiInput")
                .param("judgeSystem", "Simple")
                .param("title", "createSimpleProblemWithMultiInputTitle")
                .param("detail", "createSimpleProblemWithMultiInputDetail")
                .param("timeLimit", "1000")
                .param("memoryLimit", "65536")
                .param("input", "input1")
                .param("input", "input2")
                .param("output", "output1")
                .param("output", "output2"))
                .andExpect(redirectedUrl("/problem/createSimpleProblemWithMultiInput"));
    }

    @Test
    public void createSimpleProblemProblemIdMissing() throws Exception {
        mockMvc().perform(post("/problem/create/simple").with(signInAsAdmin())
                .param("judgeSystem", "Simple")
                .param("title", "createSimpleProblemProblemIdMissingTitle")
                .param("detail", "createSimpleProblemProblemIdMissingDetail")
                .param("timeLimit", "1000")
                .param("memoryLimit", "65536")
                .param("input", "input")
                .param("output", "output1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createSimpleProblemJudgeSystemMissing() throws Exception {
        mockMvc().perform(post("/problem/create/simple").with(signInAsAdmin())
                .param("problemId", "createSimpleProblemJudgeSystemMissing")
                .param("title", "createSimpleProblemJudgeSystemMissingTitle")
                .param("detail", "createSimpleProblemJudgeSystemMissingDetail")
                .param("timeLimit", "1000")
                .param("memoryLimit", "65536")
                .param("input", "input")
                .param("output", "output1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createSimpleProblemTitleMissing() throws Exception {
        mockMvc().perform(post("/problem/create/simple").with(signInAsAdmin())
                .param("problemId", "createSimpleProblemTitleMissing")
                .param("judgeSystem", "Simple")
                .param("detail", "createSimpleProblemTitleMissingDetail")
                .param("timeLimit", "1000")
                .param("memoryLimit", "65536")
                .param("input", "input")
                .param("output", "output1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createSimpleProblemDetailMissing() throws Exception {
        mockMvc().perform(post("/problem/create/simple").with(signInAsAdmin())
                .param("problemId", "createSimpleProblemDetailMissing")
                .param("judgeSystem", "Simple")
                .param("title", "createSimpleProblemDetailMissingTitle")
                .param("timeLimit", "1000")
                .param("memoryLimit", "65536")
                .param("input", "input")
                .param("output", "output"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createSimpleProblemTimeLimitMissing() throws Exception {
        mockMvc().perform(post("/problem/create/simple").with(signInAsAdmin())
                .param("problemId", "createSimpleProblemTimeLimitMissing")
                .param("judgeSystem", "Simple")
                .param("title", "createSimpleProblemTimeLimitMissingTitle")
                .param("detail", "createSimpleProblemTimeLimitMissingDetail")
                .param("memoryLimit", "65536")
                .param("input", "input")
                .param("output", "output1"))
                .andExpect(redirectedUrl("/problem/createSimpleProblemTimeLimitMissing"));
    }

    @Test
    public void createSimpleProblemMemoryLimitMissing() throws Exception {
        mockMvc().perform(post("/problem/create/simple").with(signInAsAdmin())
                .param("problemId", "createSimpleProblemMemoryLimitMissing")
                .param("judgeSystem", "Simple")
                .param("title", "createSimpleProblemMemoryLimitMissingTitle")
                .param("detail", "createSimpleProblemMemoryLimitMissingDetail")
                .param("timeLimit", "1000")
                .param("input", "input")
                .param("output", "output1"))
                .andExpect(redirectedUrl("/problem/createSimpleProblemMemoryLimitMissing"));
    }

    @Test
    public void createSimpleProblemInputMissing() throws Exception {
        mockMvc().perform(post("/problem/create/simple").with(signInAsAdmin())
                .param("problemId", "createSimpleProblemInputMissing")
                .param("judgeSystem", "Simple")
                .param("title", "createSimpleProblemInputMissingTitle")
                .param("detail", "createSimpleProblemInputMissingDetail")
                .param("timeLimit", "1000")
                .param("memoryLimit", "65536")
                .param("output", "output"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createSimpleProblemOutputMissing() throws Exception {
        mockMvc().perform(post("/problem/create/simple").with(signInAsAdmin())
                .param("problemId", "createSimpleProblemOutputMissing")
                .param("judgeSystem", "Simple")
                .param("title", "createSimpleProblemOutputMissingTitle")
                .param("detail", "createSimpleProblemOutputMissingMissing")
                .param("timeLimit", "1000")
                .param("memoryLimit", "65536")
                .param("input", "input"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createSimpleProblemSameProblemId() throws Exception {
        mockMvc().perform(post("/problem/create/simple").with(signInAsAdmin())
                .param("problemId", "createSimpleProblemSameProblemId")
                .param("judgeSystem", "Simple")
                .param("title", "createSimpleProblemSameProblemIdTitle")
                .param("detail", "createSimpleProblemSameProblemIdDetail")
                .param("timeLimit", "1000")
                .param("memoryLimit", "65536")
                .param("input", "input")
                .param("output", "output"))
                .andExpect(redirectedUrl("/problem/createSimpleProblemSameProblemId"));

        mockMvc().perform(post("/problem/create/simple").with(signInAsAdmin())
                .param("problemId", "createSimpleProblemSameProblemId")
                .param("judgeSystem", "Simple")
                .param("title", "createSimpleProblemSameProblemIdTitle")
                .param("detail", "createSimpleProblemSameProblemIdDetail")
                .param("timeLimit", "1000")
                .param("memoryLimit", "65536")
                .param("input", "input")
                .param("output", "output"))
                .andExpect(status().isBadRequest());
    }
}
