package com.lagrange.infi.config.oauth;

import com.lagrange.infi.data.entity.MemberE;
import com.lagrange.infi.data.repository.MemberRepository;
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

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("getClientRegistration : " + userRequest.getClientRegistration());
        log.info("getAccessToken : " + userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes : " + oAuth2User.getAttributes());
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String provider = clientRegistration.getClientId();
        String providerId = attributes.get("sub").toString();
        String email = attributes.get("email").toString();
//        String password = passwordEncoder.encode(clientRegistration.getClientSecret());
        String name = attributes.get("name").toString();
        String role = "ROLE_USER";

//        MemberE memberE = memberRepository.findByUserid(name);
//        if (memberE == null){
//            memberRepository.save(MemberE.builder().userid(name).password(password)
//                    .email(email).role(role).providerid(providerId).provider(provider).build());
//        }

        return oAuth2User;
    }
}
