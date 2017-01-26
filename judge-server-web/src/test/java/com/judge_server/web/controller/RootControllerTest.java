package com.judge_server.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void root() throws Exception {
        mockMvc().perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/root/index"));
    }
}
