package com.lagrange.infi.data.service.impl;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

import com.lagrange.infi.data.dto.MemberD;
import com.lagrange.infi.data.entity.MemberE;
import com.lagrange.infi.data.repository.MemberRepository;
import com.lagrange.infi.data.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
//@RequiredArgsConstructor //final , notnull 어노테이션 붙은 객체에 생성자 자동 주입
public class MemberServiceImpl implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private MemberRepository memberRepository;
    private String user_role = "user";

    @Autowired
    public MemberServiceImpl(PasswordEncoder passwordEncoder,MemberRepository memberRepository){
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberD register(String userid,String password,String email){
        if (memberRepository.existsByUserid(userid)){
            log.info(userid + "이미 있는 id");
        }

        String encodedPassword = passwordEncoder.encode(password);
        log.info(userid + " " + password);
        MemberE memberE = MemberE.builder()
                .userid(userid)
                .password(encodedPassword)
                .email(email)
                .role(user_role)
                .build();

        memberRepository.save(memberE);
        return new MemberD(memberE.getId(),userid,password,email);
    }

    @Override
    public MemberD update(Long id, String userid, String password,String email){
        String encodedPassword = passwordEncoder.encode(password);
        MemberE memberE = MemberE.builder()
                .id(id)
                .userid(userid)
                .password(encodedPassword)
                .email(email)
                .role(user_role)
                .build();

        memberRepository.save(memberE);
        return new MemberD(id,userid,password,email);
    }

    @Override
    public boolean login(String userid,String password){
        if (passwordEncoder.matches(password,memberRepository.findByUserid(userid).getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public MemberD getId(Long id){
        MemberE memberE = memberRepository.findByid(id);
        MemberD memberD = MemberD.builder()
                .id(memberE.getId())
                .userid(memberE.getUserid())
                .password(memberE.getPassword())
                .email(memberE.getEmail())
                .build();
        return memberD;
    }



}
