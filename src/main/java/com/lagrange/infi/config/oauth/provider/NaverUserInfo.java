package com.lagrange.infi.config.oauth.provider;

import java.util.LinkedHashMap;
import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo{

    private LinkedHashMap<String,Object> attributes;

    public NaverUserInfo(LinkedHashMap<String,Object> attributes){
        this.attributes = attributes;
    }
    @Override
    public String getProviderID() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        return attributes.get("nickname").toString();
    }
}
