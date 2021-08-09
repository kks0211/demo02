package com.board.mapper;

import com.board.config.RootConfig;
import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.test.DataSourceOracleTests;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class BoardMapperTest {

    static final Logger log = LoggerFactory.getLogger(BoardMapperTest.class);

    @Setter(onMethod_ = @Autowired)
    private BoardMapper boardMapper;

    @Test
    public void testGetList() {
        boardMapper.getList().forEach(board -> log.info("board : {} ", board));
    }

    @Test
    public void testInsert() {
        BoardVO vo = BoardVO.builder().title("test1").content("test").writer("test1").build();

        boardMapper.insert(vo);
        log.info("vo : {}" +vo);
    }

    @Test
    public void testInsertSelectKey() {
        BoardVO vo = BoardVO.builder().title("test2").content("test2").writer("test12").build();

        boardMapper.insertSelectKey(vo);
        log.info("vo : {}" +vo);
    }

    @Test
    public void testRead() {

        BoardVO vo = boardMapper.read(3L);
        log.info("vo : {}" +vo);
    }

    @Test
    public void testDelete() {
        log.info("Delete Result : " + boardMapper.delete(3L));
    }

    @Test
    public void testUpdate() {
        BoardVO vo = BoardVO.builder().title("수정").content("수정").writer("수정").bno(4L).build();
        int result = boardMapper.update(vo);
        log.info("result : {}",result);
    }

    @Test
    public void testPaging(){
        Criteria cri = new Criteria();
        cri.setType("T");
        cri.setKeyword("test");
        cri.setPageNum(1);
        cri.setAmount(10);

        List<BoardVO> list = boardMapper.getListWithPaging(cri);

        list.forEach(board -> log.info("board : {}", board));
    }

}
