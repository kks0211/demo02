package com.board.mapper;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.Search;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardMapper {

    //@Select("select * from tbl_board where bno > 0")
    public List<BoardVO> getList();

    public void insert(BoardVO vo);

    public void insertSelectKey(BoardVO vo);

    public BoardVO read(Long bno);

    public int delete(Long bno);

    public int update(BoardVO vo);

    public List<BoardVO> getListWithPaging(Search search);

    public int getTotalCount(Criteria cri);

    public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}
