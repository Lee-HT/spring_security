package com.lagrange.infi.config.auth;

import com.lagrange.infi.data.entity.MemberE;
import com.lagrange.infi.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipleDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        log.info("PrincipleService userid = "+userid);
        MemberE memberE = memberRepository.findByUserid(userid);
        if(memberE != null){
            return new PrincipalDetails(memberE);
        }
        return null;
    }

}
