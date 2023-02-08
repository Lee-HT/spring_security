package com.lagrange.infi.config.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("getClientRegistation : " + userRequest.getClientRegistration());
        log.info("getAccessToken : " + userRequest.getAccessToken().getTokenValue());
        log.info("getAttributes : " + super.loadUser(userRequest).getAttributes());
        return super.loadUser(userRequest);
    }
}
