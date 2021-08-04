package com.board.dao;

import com.board.domain.BoardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO {
    @Override
    public void create(BoardVO vo) throws Exception {

    }

    @Override
    public BoardVO read(Integer bno) throws Exception {
        return null;
    }

    @Override
    public BoardVO update(BoardVO vo) throws Exception {
        return null;
    }

    @Override
    public Integer delete(Integer bno) throws Exception {
        return null;
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return null;
    }

    /*private final SqlSession session;

    private static String namespace = "com.board.mapper.BoardMapper";

    @Override
    public void create(BoardVO vo) throws Exception {
        session.insert(namespace + ".create", vo);
    }

    @Override
    public BoardVO read(Integer bno) throws Exception {
        return session.selectOne(namespace + ".read", bno);
    }

    @Override
    public BoardVO update(BoardVO vo) throws Exception {
        session.update(namespace + ".update", vo);
        return vo;
    }

    @Override
    public Integer delete(Integer bno) throws Exception {
        session.delete(namespace + ".delete", bno);
        return bno;
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return session.selectList(namespace + ".listAll");
    }*/
}
