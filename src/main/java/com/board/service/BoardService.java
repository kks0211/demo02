package com.board.service;

import com.board.domain.BoardVO;

import java.util.List;

public interface BoardService {
    public void regist(BoardVO vo);

    public BoardVO read(Long bno);

    public boolean modify(BoardVO vo);

    public boolean remove(Long bno);

    public List<BoardVO> getList();
}
