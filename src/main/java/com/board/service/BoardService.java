package com.board.service;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.Search;

import java.util.List;

public interface BoardService {
    public void regist(BoardVO vo);

    public BoardVO read(Long bno);

    public boolean modify(BoardVO vo);

    public boolean remove(Long bno);

    //public List<BoardVO> getList();

    public List<BoardVO> getList(Criteria cri);

    public int getTotalCount(Criteria cri);
}
