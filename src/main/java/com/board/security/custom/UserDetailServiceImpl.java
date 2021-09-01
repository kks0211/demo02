package com.board.security.custom;

import com.board.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {
    //  4. 유저정보를 아이디로 찾아서 CustomUserDetails에 담아  리턴하는 로직
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        //return CustomUserDetails(memberMapper.findByUserId(userid));
        return null;
    }

}
