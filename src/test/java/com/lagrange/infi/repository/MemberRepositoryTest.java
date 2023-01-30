package com.lagrange.infi.repository;

import com.lagrange.infi.data.entity.MemberE;
import com.lagrange.infi.data.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
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

        memberRepository.save(memberE);
        log.info(memberE.toString());
    }

}
