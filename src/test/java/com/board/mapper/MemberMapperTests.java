package com.board.mapper;

import com.board.config.TestConfiguration;
import com.board.domain.MemberVO;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestConfiguration
class MemberMapperTests {

    @Setter(onMethod_ = @Autowired)
    private MemberMapper memberMapper;

    @Setter(onMethod_ = @Autowired)
    private PasswordEncoder passwordEncoder;

    static final Logger log = LoggerFactory.getLogger(BoardMapperTest.class);

    @Test
    void read() {
        MemberVO vo = memberMapper.read("admin00");
        log.info("vo : {}", vo);

        vo.getAuthList().forEach(authVO -> log.info("{}", authVO));
    }

    @Test
    void testEnc(){
        log.info("{}", passwordEncoder.encode("pw00"));
    }
}