package com.board.mapper;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.MemberVO;
import com.board.domain.Search;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberMapper {

    MemberVO read(String userid);
}
