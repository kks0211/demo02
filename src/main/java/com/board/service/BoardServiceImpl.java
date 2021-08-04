package com.board.service;

import com.board.domain.BoardVO;
import com.board.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    private BoardMapper boardMapper;

    @Override
    public void regist(BoardVO vo) {
        boardMapper.insertSelectKey(vo);
    }

    @Override
    public BoardVO read(Long bno) {
        return boardMapper.read(bno);
    }

    @Override
    public boolean modify(BoardVO vo) {
        return boardMapper.update(vo) == 1;

    }

    @Override
    public boolean remove(Long bno) {
        return boardMapper.delete(bno) == 1;
    }

    @Override
    public List<BoardVO> getList() {
        return boardMapper.getList();
    }
}
