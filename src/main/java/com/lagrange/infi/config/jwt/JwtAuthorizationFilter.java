package com.lagrange.infi.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lagrange.infi.config.auth.PrincipalDetails;
import com.lagrange.infi.data.entity.MemberE;
import com.lagrange.infi.data.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);

        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        String secret = "secretKey";
//        super.doFilterInternal(request, response, chain);
        log.info("인증 , 권한 필요한 주소 요청됨");

        String jwtHeader = request.getHeader("Authorization");
        log.info("jwtHeader : " + jwtHeader);

        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")){
            chain.doFilter(request,response);
            return;
        }

        String jwtToken = jwtHeader.replace("Bearer ", "");

        String userid = JWT.require(Algorithm.HMAC512(jwtProperties.SecretKey)).build().verify(jwtToken).getClaim("userid").asString();

        if(userid != null){
            MemberE memberE = memberRepository.findByUserid(userid);
            log.info(memberE.toString());

            PrincipalDetails principalDetails = new PrincipalDetails(memberE);
            log.info("PrincipalDetails : " + principalDetails.getUsername());
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails,null,principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request,response);
        }
    }
}
