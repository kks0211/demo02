package com.board.controller;

import com.board.config.RootConfig;
import com.board.config.ServletConfig;
import com.board.domain.SampleVO;
import com.google.gson.Gson;
import lombok.Setter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
@ActiveProfiles("real")
public class SampleControllerTest {

    private static final Logger log = LoggerFactory.getLogger(SampleControllerTest.class);

    @Setter(onMethod_ = {@Autowired})
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test() throws Exception {
        SampleVO vo = SampleVO.builder().mno(1).firstName("kim").lastName("kwan").build();
        String jsonStr = new Gson().toJson(vo);

        log.info("json : {} ", jsonStr);

        mvc.perform(post("/sample/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr)).andExpect(status().is(200));


    }


}