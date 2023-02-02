package com.lagrange.infi.config.auth;

import com.lagrange.infi.data.entity.MemberE;
import com.lagrange.infi.data.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrincipleDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        log.info("username = "+id);
        MemberE memberE = memberRepository.findById(id);
        if(memberE != null){
            return new PrincipalDetails(memberE);
        }
        return null;
    }

}
