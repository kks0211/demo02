package com.board.mapper;

import com.board.config.RootConfig;
import com.board.domain.Criteria;
import com.board.domain.ReplyVO;
import lombok.Setter;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReplyMapperTests {
    static final Logger log = LoggerFactory.getLogger(ReplyMapperTests.class);
    //private Long[] bnoArr = {3145745L, 3145744L, 3145743L, 3145742L, 3145741L};
    private Long[] bnoArr = {1L, 2L, 3L, 4L, 5L};

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper replyMapper;

    @Test
    public void test1Mapper() {
        log.info("replyMapper : {}", replyMapper);
    }

    @Test
    public void test2Create() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO vo = ReplyVO.builder().bno(bnoArr[i % 5]).reply("댓글 테스트" + i).replyer("replyer" + i).build();
            log.info("reply : {}", vo);
            replyMapper.insert(vo);
        });
    }

    @Test
    public void test3Read() {
        Long targetRno = 2L;
        ReplyVO vo = replyMapper.read(targetRno);
        log.info("read : {}", vo);
    }

    @Test
    public void test4Update() {
        Long targetRno = 2L;
        ReplyVO vo = replyMapper.read(targetRno);
        vo.setReply("update reply");
        int count = replyMapper.update(vo);
        log.info("update count : {}", count);
    }

    @Test
    public void test5Delete() {
        Long tagetRno = 2L;
        replyMapper.delete(tagetRno);
    }

    @Test
    public void test6List() {
        Criteria cri = new Criteria();

        List<ReplyVO> replies = replyMapper.getListWithPaging(cri, bnoArr[0]);

        replies.forEach(reply -> log.info("reply : {}", reply));
    }

    @Test
    public void test7List() {
        Criteria cri = new Criteria(1, 10);
        List<ReplyVO> replies = replyMapper.getListWithPaging(cri, 1L);
        replies.forEach(reply -> log.info("{}",reply));
    }

}