package com.board.controller;

import com.board.config.RootConfigDev;
import com.board.config.RootConfigReal;
import com.board.config.ServletConfig;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Log4j
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfigDev.class, RootConfigReal.class, ServletConfig.class})
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardControllerTest {

    @Setter(onMethod_ = @Autowired)
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void list() throws Exception {
        log.info(mvc.perform(MockMvcRequestBuilders.get("/board/list")).
                andReturn().getModelAndView().getModelMap());
    }

    @Test
    public void test1Regist() throws Exception {
        String resultPage = mvc.perform(MockMvcRequestBuilders.post("/board/register")
                        .param("title", "테스트 등록")
                        .param("content", "테스트 등록")
                        .param("writer", "user0"))
                .andReturn().getModelAndView().getViewName();
        log.info(resultPage);
    }

    @Test
    public void test2Get() throws Exception {
        log.info(mvc.perform(MockMvcRequestBuilders.get("/board/get").
                        param("bno", "1"))
                .andReturn().getModelAndView().getModelMap());
    }

    @Test
    public void test3Update() throws Exception {
        log.info(mvc.perform(MockMvcRequestBuilders.post("/board/modify")
                        .param("bno", "1")
                        .param("title", "수정")
                        .param("content", "수정내용1")
                        .param("writer", "user00"))
                .andReturn().getModelAndView().getViewName());
    }

    @Test
    public void test5Delete() throws Exception {
        String resultPage = mvc.perform(MockMvcRequestBuilders.post("/board/remove")
                        .param("bno", "1"))
                .andReturn().getModelAndView().getViewName();

        log.info(resultPage);

    }

    @Test
    public void test4ListPaging() throws Exception {
        log.info(mvc.perform(MockMvcRequestBuilders.get("/board/list").param("pageNum", "1").param("amount", "10"))
                .andReturn().getModelAndView().getModelMap());
    }
}