package com.lagrange.infi.data.service.impl;

import com.lagrange.infi.data.dto.MemberD;
import com.lagrange.infi.data.entity.MemberE;
import com.lagrange.infi.data.repository.MemberRepository;
import com.lagrange.infi.data.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor //final , notnull 어노테이션 붙은 객체에 생성자 자동 주입
public class MemberServiceImpl implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(PasswordEncoder passwordEncoder,MemberRepository memberRepository){
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberD register(String id,String password){
        String encodedPassword = passwordEncoder.encode(password);
        MemberE memberE = MemberE.builder()
//                .idx(1L)
                .id(id)
                .password(password)
                .build();

        memberRepository.save(memberE);
        return new MemberD(memberE.getIdx(),id,password);
    }

    @Override
    public MemberD update(Long idx, String id, String password){
        MemberE memberE = MemberE.builder()
                .idx(idx)
                .id(id)
                .password(password)
                .build();
        memberRepository.save(memberE);
        return new MemberD(idx,id,password);
    }

    @Override
    public boolean login(String id,String password){
        return true;

    }



}
