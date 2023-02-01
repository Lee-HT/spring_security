package com.lagrange.infi.repository;

import com.lagrange.infi.data.entity.MemberE;
import com.lagrange.infi.data.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void create(){
        MemberE memberE =
                MemberE.builder()
                        .idx(1L)
                        .id("id")
                        .password("password")
                        .build();

        MemberE memberE1 = memberRepository.save(memberE);
        log.info(memberE1.toString());
    }

}
