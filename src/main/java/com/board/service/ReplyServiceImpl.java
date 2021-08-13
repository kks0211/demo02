package com.board.service;

import com.board.domain.Criteria;
import com.board.domain.ReplyPageDTO;
import com.board.domain.ReplyVO;
import com.board.mapper.BoardMapper;
import com.board.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private ReplyMapper replyMapper;

    private BoardMapper boardMapper;

    private static final Logger log = LoggerFactory.getLogger(ReplyServiceImpl.class);

    @Transactional
    @Override
    public int regist(ReplyVO vo) {
        log.info("reply regist : {}", vo);
        boardMapper.updateReplyCnt(vo.getBno(), 1);
        return replyMapper.insert(vo);
    }

    @Override
    public ReplyVO read(Long rno) {
        log.info("reply read : {}", rno);
        return replyMapper.read(rno);
    }

    @Override
    public int modify(ReplyVO vo) {
        log.info("reply modify : {}", vo);
        return replyMapper.update(vo);
    }

    @Transactional
    @Override
    public int remove(Long rno) {
        log.info("reply remove : {}", rno);
        ReplyVO vo = replyMapper.read(rno);
        boardMapper.updateReplyCnt(vo.getBno(), -1);
        return replyMapper.delete(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno) {

        return replyMapper.getListWithPaging(cri, bno);
    }

    @Override
    public ReplyPageDTO getListPage(Criteria cri, Long bno) {

        return new ReplyPageDTO(replyMapper.getCountByBno(bno), replyMapper.getListWithPaging(cri, bno));
    }
}
