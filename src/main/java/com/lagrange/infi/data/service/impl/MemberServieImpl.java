package com.lagrange.infi.data.service.impl;

import com.lagrange.infi.data.dto.Members;
import com.lagrange.infi.data.repository.MemberRepository;
import com.lagrange.infi.data.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor //final , notnull 어노테이션 붙은 객체에 생성자 자동 주입
public class MemberServieImpl implements MemberService {
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public MemberServieImpl(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    public void register(String id,String password){
        String encodedPassword = passwordEncoder.encode(password);
        Members member = new Members(id, encodedPassword);

        MemberRepository.save(member);
    }

    public boolean login(String id,String password){

    }



}
