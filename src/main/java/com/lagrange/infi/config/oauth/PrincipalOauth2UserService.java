package com.lagrange.infi.config.oauth;

import com.lagrange.infi.config.auth.PrincipalDetails;
import com.lagrange.infi.config.oauth.provider.GoogleUserInfo;
import com.lagrange.infi.config.oauth.provider.NaverUserInfo;
import com.lagrange.infi.config.oauth.provider.OAuth2UserInfo;
import com.lagrange.infi.data.entity.MemberE;
import com.lagrange.infi.data.repository.MemberRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {


        OAuth2User oAuth2User = super.loadUser(userRequest);
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Object accessToken = userRequest.getAccessToken().getTokenValue();

        log.info("getClientRegistration : " + clientRegistration);
        log.info("getAccessToken : " + accessToken);
        log.info("getAttributes : " + attributes);

        String registrationId = clientRegistration.getRegistrationId();


        OAuth2UserInfo oAuth2UserInfo = null;
        if(registrationId.equals("google")){
            log.info("구글 로그인");
            oAuth2UserInfo = new GoogleUserInfo(attributes);

        }else if(registrationId.equals("naver")){
            log.info("네이버 로그인");
//            log.info(attributes.get("response").getClass().getName());
            oAuth2UserInfo = new NaverUserInfo((LinkedHashMap<String, Object>) attributes.get("response"));

        }else{

        };

        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderID();
        String email = oAuth2UserInfo.getEmail();
        String password = passwordEncoder.encode(providerId);
        String name = provider + "_"+ providerId;
        String role = "ROLE_USER";

        MemberE memberE = memberRepository.findByUserid(name);
        if (memberE == null){
            memberE = MemberE.builder().userid(name).password(password)
                    .email(email).role(role).providerid(providerId).provider(provider).build();
            log.info(memberE.getUserid().toString() + " 님 가입 되셨습니다");
            memberRepository.save(memberE);
        }else{
            log.info(memberE.getUserid().toString() + " 님 기존 회원 입니다");

        }

        return new PrincipalDetails(memberE,oAuth2User.getAttributes());
    }
}
