package com.board.mapper;

import com.board.config.TestConfiguration;
import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@TestConfiguration
public class BoardMapperTest {
    static final Logger log = LoggerFactory.getLogger(BoardMapperTest.class);

    @Setter(onMethod_ = @Autowired)
    private BoardMapper boardMapper;

    @BeforeEach
    void beforeEach() {
        String title = "test title";
        String content = "test content";
        String writer = "test writer";

        BoardVO board = BoardVO.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();

        boardMapper.insertSelectKey(board);
    }

    @Test
    public void testGetList() {
        //boardMapper.getList().forEach(board -> log.info("board : {} ", board));

        log.info("{}",boardMapper.getList().stream().collect(Collectors.toList()));
        //log.info("{}",boardMapper.getList().stream().collect(Collectors.toMap(BoardVO::getBno, BoardVO::getContent)));

    }

    @Test
    public void testInsert() {
        BoardVO vo = BoardVO.builder()
                .title("test1")
                .content("test")
                .writer("test1")
                .build();

        log.info("vo : {}", vo);
        boardMapper.insert(vo);
    }

    @Test
    public void testInsertSelectKey() {
        BoardVO vo = BoardVO.builder().title("test2").content("test2").writer("test12").build();

        boardMapper.insertSelectKey(vo);
        log.info("vo : {}", vo);
    }

    @Test
    public void testRead() {

        BoardVO vo = boardMapper.read(3L);
        log.info("vo : {}", vo);
    }

    //@Test
    public void testDelete() {
        log.info("Delete Result : " + boardMapper.delete(3L));
    }

    @Test
    public void testUpdate() {
        BoardVO vo = BoardVO.builder().title("수정").content("수정").writer("수정").build();
        vo.setBno(4L);
        int result = boardMapper.update(vo);
        log.info("result : {}", result);
    }

    @Test
    public void testPaging() {
        Criteria cri = new Criteria();
        cri.setType("T");
        cri.setKeyword("test");
        cri.setPageNum(1);
        cri.setAmount(10);

        //List<BoardVO> list = boardMapper.getListWithPaging(cri, category);

        //list.forEach(board -> log.info("board : {}", board));
    }

}
