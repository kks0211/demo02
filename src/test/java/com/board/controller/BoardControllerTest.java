package com.board.controller;

import com.board.config.ServletConfig;
import com.board.config.TestConfiguration;
import com.board.service.BoardService;
import lombok.extern.log4j.Log4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Log4j
@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
//@ActiveProfiles("real")
@TestConfiguration
@ContextConfiguration(classes = {ServletConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardControllerTest {

    /*@Autowired
    private WebApplicationContext webApplicationContext;

    private BoardController boardController;

    */

    @Mock
    BoardService boardService;

    @InjectMocks
    private BoardController boardController;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        //System.out.println(webApplicationContext);
        //mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.mvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }

    @Test
    public void list() throws Exception {
        mvc.perform(get("/board/list"));

//        log.info(mvc.perform(MockMvcRequestBuilders.get("/board/list")).
//                andReturn().getModelAndView().getModelMap());
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
        log.info(mvc.perform(get("/board/get").
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
        log.info(mvc.perform(get("/board/list").param("pageNum", "1").param("amount", "10"))
                .andReturn().getModelAndView().getModelMap());
    }
}