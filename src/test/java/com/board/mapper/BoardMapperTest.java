package com.board.mapper;

import com.board.config.RootConfig;
import com.board.domain.BoardVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class BoardMapperTest {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper boardMapper;

    @Test
    public void testGetList() {
        boardMapper.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testInsert() {
        BoardVO vo = BoardVO.builder().title("test1").content("test").writer("test1").build();

        boardMapper.insert(vo);
        log.info(vo);
    }

    @Test
    public void testInsertSelectKey() {
        BoardVO vo = BoardVO.builder().title("test2").content("test2").writer("test12").build();

        boardMapper.insertSelectKey(vo);
        log.info(vo);
    }

    @Test
    public void testRead() {

        BoardVO vo = boardMapper.read(3L);
        log.info(vo);
    }

    @Test
    public void testDelete() {
        log.info("Delete Result : " + boardMapper.delete(3L));
    }

    @Test
    public void testUpdate() {
        BoardVO vo = BoardVO.builder().title("수정").content("수정").writer("수정").bno(4L).build();
        int result = boardMapper.update(vo);
        log.info(result);
    }

}
