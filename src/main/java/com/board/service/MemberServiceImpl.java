package com.board.service;

import com.board.controller.ReplyController;
import com.board.domain.MemberVO;
import com.board.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private static final Logger log = LoggerFactory.getLogger(ReplyController.class);

    private MemberMapper memberMapper;

    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public int joinUser(MemberVO vo) {

        String userpw = passwordEncoder.encode(vo.getUserpw());
        vo.setUserpw(userpw);

        int result = memberMapper.joinUser(vo);

        if (result == 1) {
            memberMapper.joinUserAuth(vo.getUserid(), "ROLE_ADMIN");
        }

        //return memberMapper.joinUserAuth(vo.getUserid(), "ROLE_ADMIN");
        return result;
    }
}
