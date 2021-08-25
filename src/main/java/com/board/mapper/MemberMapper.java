package com.board.mapper;

import com.board.domain.MemberVO;

public interface MemberMapper {

    MemberVO read(String userid);
}
