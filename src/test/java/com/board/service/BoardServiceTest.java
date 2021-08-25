package com.board.service;

import com.board.config.RootConfig;
import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.Search;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration(classes = RootConfig.class)
public class BoardServiceTest {

    @Setter(onMethod_ = @Autowired)
    private BoardService boardService;

    @Test
    public void regist() {
        BoardVO vo = BoardVO.builder().title("서비스 테스트").content("서비스 테스트").writer("서비스").build();
        boardService.regist(vo);
        log.info("번호 : " + vo.getBno());
    }

    @Test
    public void read() {
        BoardVO vo = new BoardVO();
        vo.setBno(5L);
        log.info(boardService.read(vo.getBno()));
    }

    @Test
    public void modify() {
        BoardVO vo = boardService.read(5L);
        vo.setTitle("수정");
        vo.setContent("수정");

        log.info(" 결과 : " + boardService.modify(vo));

    }

    @Test
    public void remove() {
        log.info(boardService.remove(5L));
    }

    @Test
    public void getList() {

        // boardService.getList().forEach(vo -> log.info(vo));
        //boardService.getList(new Criteria(2, 10)).forEach(vo -> log.info(vo));
    }
}