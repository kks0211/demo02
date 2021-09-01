package com.board.mapper;

import com.board.domain.MemberVO;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {

    MemberVO read(String userid);

    //String findByUserId(String userid);

    int joinUser(MemberVO vo);

    int joinUserAuth(@Param("userid") String userid, @Param("auth") String auth);
}
