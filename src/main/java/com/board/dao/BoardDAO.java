package com.board.dao;

import com.board.domain.BoardVO;

import java.util.List;

public interface BoardDAO {

    public void create(BoardVO vo) throws Exception;

    public BoardVO read(Integer bno) throws Exception;

    public BoardVO update(BoardVO vo) throws Exception;

    public Integer delete(Integer bno) throws Exception;

    public List<BoardVO> listAll() throws Exception;
}
