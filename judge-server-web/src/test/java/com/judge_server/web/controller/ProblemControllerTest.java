package com.judge_server.web.controller;

import com.judge_server.core.entity.judge.Problem;
import com.judge_server.core.repository.judge.ProblemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProblemControllerTest extends AbstractControllerTest {

    @Autowired
    private ProblemRepository problemRepository;

    @Test
    public void getProblemList() throws Exception {
        MvcResult mvcResult = mockMvc().perform(get("/problem").with(signIn()))
                .andExpect(status().isOk())
                .andExpect(view().name("/problem/list"))
                .andReturn();

        ModelMap modelMap = mvcResult.getModelAndView().getModelMap();
        Page<Problem> page = (Page<Problem>) modelMap.get("problemList");
        assertThat(page, is(not(nullValue())));
        assertThat(page.getTotalElements(), is(equalTo(expectedTotalElements())));
        assertThat(page.getNumberOfElements(), is(equalTo(expectedNumberOfElements(0, 10))));
    }

    @Test
    public void getProblemListWithPage() throws Exception {
        MvcResult mvcResult = mockMvc().perform(get("/problem").param("page", "9").with(signIn()))
                .andExpect(status().isOk())
                .andExpect(view().name("/problem/list"))
                .andReturn();

        ModelMap modelMap = mvcResult.getModelAndView().getModelMap();
        Page<Problem> page = (Page<Problem>) modelMap.get("problemList");
        assertThat(page, is(not(nullValue())));
        assertThat(page.getTotalElements(), is(equalTo(expectedTotalElements())));
        assertThat(page.getNumberOfElements(), is(equalTo(expectedNumberOfElements(9, 10))));
    }

    @Test
    public void getProblemListWithSize() throws Exception {
        MvcResult mvcResult = mockMvc().perform(get("/problem").param("size", "20").with(signIn()))
                .andExpect(status().isOk())
                .andExpect(view().name("/problem/list"))
                .andReturn();

        ModelMap modelMap = mvcResult.getModelAndView().getModelMap();
        Page<Problem> page = (Page<Problem>) modelMap.get("problemList");
        assertThat(page, is(not(nullValue())));
        assertThat(page.getTotalElements(), is(equalTo(expectedTotalElements())));
        assertThat(page.getNumberOfElements(), is(equalTo(expectedNumberOfElements(0, 20))));
    }

    @Test
    public void getProblemListOverflow() throws Exception {
        MvcResult mvcResult = mockMvc().perform(get("/problem").param("page", "11").with(signIn()))
                .andExpect(status().isOk())
                .andExpect(view().name("/problem/list"))
                .andReturn();

        ModelMap modelMap = mvcResult.getModelAndView().getModelMap();
        Page<Problem> page = (Page<Problem>) modelMap.get("problemList");
        assertThat(page, is(not(nullValue())));
        assertThat(page.getTotalElements(), is(equalTo(expectedTotalElements())));
        assertThat(page.getNumberOfElements(), is(equalTo(expectedNumberOfElements(11, 10))));
    }

    @Test
    public void getProblem() throws Exception {
        mockMvc().perform(get("/problem/problem-1").with(signIn()))
                .andExpect(status().isOk())
                .andExpect(view().name("/problem/index"));
    }

    @Test
    public void getProblemNotFound() throws Exception {
        mockMvc().perform(get("/problem/problem-100").with(signIn()))
                .andExpect(status().isNotFound());
    }

    private long expectedTotalElements() {
        return problemRepository.findAll().size();
    }

    private int expectedNumberOfElements(int page, int size) {
        long expectedTotalElements = expectedTotalElements();
        long diff = expectedTotalElements - page * size;

        if (diff < 0) {
            return 0;
        } else if (diff <= size) {
            return (int) diff;
        } else {
            return size;
        }
    }

}
