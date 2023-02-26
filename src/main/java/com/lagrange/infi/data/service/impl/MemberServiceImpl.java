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

    //DB엔 ROLE 붙어서 저장 해야함
    private String user_role = "ROLE_USER";

    @Autowired
    public MemberServiceImpl(PasswordEncoder passwordEncoder,MemberRepository memberRepository){
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
    }

    //회원 가입
    @Override
    public MemberD register(String userid,String password,String email){
        Long id;
        if (memberRepository.existsByUserid(userid)){
            log.info(userid + " 이미 있는 id");
            id = null;
        }else{
            String encodedPassword = passwordEncoder.encode(password);
            log.info(userid + " " + password);
            MemberE memberE = MemberE.builder()
                    .userid(userid)
                    .password(encodedPassword)
                    .email(email)
                    .role(user_role)
                    .build();

            memberRepository.save(memberE);
            id = memberE.getId();
        }


        return new MemberD(id,userid,password,email);
    }

    //회원 탈퇴
    @Override
    public boolean unregister(String userid,String password){
        MemberE memberE = memberRepository.findByUserid(userid);

        if (passwordEncoder.matches(password,memberE.getPassword())){
            log.info(memberE.toString());
            memberRepository.deleteByUserid(userid);
            return true;
        }
        return false;
    }

    //회원 정보 수정
    @Override
    public MemberD update(Long id, String userid, String password,String email,String newPassword){
        MemberE memberE = memberRepository.findByUserid(userid);
        String nowPassword = password;
        if (passwordEncoder.matches(password,memberE.getPassword())){
            String encodedPassword = passwordEncoder.encode(newPassword);
            nowPassword = newPassword;

            //Entity 값 변경
            memberE.updateInfo(encodedPassword);
            memberRepository.save(memberE); // 나중에 dirty checking 공부


            log.info("password change");
        }else{
            log.info("change fail");
        }
        return new MemberD(id,userid,nowPassword,email);
    }

    //로그인
    @Override
    public boolean login(String userid,String password){
        if (passwordEncoder.matches(password,memberRepository.findByUserid(userid).getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public void jwtlogin(MemberD memberD){
        if(memberRepository.existsByUserid(memberD.getUserid())){
            log.info("이미 있는 userid");
        }else{
            MemberE jwtMember = MemberE.builder()
                    .userid(memberD.getUserid())
                    .password(passwordEncoder.encode(memberD.getPassword()))
                    .email(memberD.getEmail())
                    .role("ROLE_USER")
                    .build();

            memberRepository.save(jwtMember);
        }
    }

    //해당 id 조회
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
