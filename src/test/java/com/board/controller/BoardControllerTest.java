package com.board.controller;

import com.board.config.RootConfig;
import com.board.config.ServletConfig;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
public class BoardControllerTest {

    @Setter(onMethod_ = @Autowired)
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void openBoardList() throws Exception {
    }

    @Test
    public void registerGet() {
    }

    @Test
    public void registerPost() {
    }

    @Test
    public void openBoardDetail() {
    }

    @Test
    public void updateBoard() {
    }

    @Test
    public void deleteBoard() {
    }

    @Test
    public void list() throws Exception {
        log.info(mvc.perform(MockMvcRequestBuilders.get("/board/list")).
                andReturn().getModelAndView().getModelMap());
    }

    @Test
    public void testRegist() throws Exception {
        String resultPage = mvc.perform(MockMvcRequestBuilders.post("/board/register")
                        .param("title", "테스트 등록")
                        .param("content", "테스트 등록")
                        .param("writer", "user0"))
                .andReturn().getModelAndView().getViewName();
        log.info(resultPage);
    }

    @Test
    public void testGet() throws Exception {
        log.info(mvc.perform(MockMvcRequestBuilders.get("/board/get").
                        param("bno", "5"))
                .andReturn().getModelAndView().getModelMap());
    }

    @Test
    public void testUpdate() throws Exception {
        log.info(mvc.perform(MockMvcRequestBuilders.post("/board/modify")
                        .param("bno", "5")
                        .param("title", "수정")
                        .param("content", "수정내용1")
                        .param("writer", "user00"))
                .andReturn().getModelAndView().getViewName());
    }

    @Test
    public void testDelete() throws Exception {
        String resultPage = mvc.perform(MockMvcRequestBuilders.post("/board/remove")
                        .param("bno", "5"))
                .andReturn().getModelAndView().getViewName();

        log.info(resultPage);

    }
}