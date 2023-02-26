package com.lagrange.infi.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagrange.infi.config.auth.PrincipalDetails;
import com.lagrange.infi.data.entity.MemberE;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/logins/jwttoken");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response)
            throws AuthenticationException {
        log.info("JwtAuthenticationFilter :  try login");
        try {
//            BufferedReader br = request.getReader();
//            log.info("br : " + br.toString());
//
//            String input = null;
//            while((input = br.readLine()) != null){
//                log.info(input);
//            }
            ObjectMapper om = new ObjectMapper();
            MemberE memberE = om.readValue(request.getInputStream(),MemberE.class);
            log.info(memberE.toString());

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(memberE.getUserid(),memberE.getPassword());

            //principalservice호출
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            log.info("member.userid : " + principalDetails.getMemberE().getUserid());

            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void successfulAuthentication(HttpServletRequest request,HttpServletResponse response,
            FilterChain filterChain, Authentication authResult)throws IOException, ServletException{
        String secret = "secretKey";
        log.info("successAuthentication 실행됨: 인증");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject("TOKEN")
                .withExpiresAt(new Date(System.currentTimeMillis()+(60000*10)))
                .withClaim("id",principalDetails.getMemberE().getId())
                .withClaim("userid",principalDetails.getMemberE().getUserid())
                .sign(Algorithm.HMAC512(secret));

        response.addHeader("Authorization","Bearer "+jwtToken);
    }
}
