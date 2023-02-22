package com.lagrange.infi.config.auth;

import com.lagrange.infi.data.entity.MemberE;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Data
//OAuth2User와 UserDetails를 동시에 사용하기 위함
public class PrincipalDetails implements UserDetails, OAuth2User {

    private MemberE memberE;
    private Map<String,Object> attribute;

    //default login
    public PrincipalDetails(MemberE memberE){
        this.memberE = memberE;
    }

    //oauth login
    //Constructor overload
    public PrincipalDetails(MemberE memberE, Map<String, Object> attribute) {
        this.memberE = memberE;
        this.attribute = attribute;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attribute;
    }

    // 해당 유저의 권한 return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> collect = new ArrayList<>();

        memberE.getRoleList().forEach(r->{
            collect.add(()->r);
        });
//        collect.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return memberE.getRole();
//            }
//        });
        return collect;
    }

    @Override
    public String getPassword(){
        return memberE.getPassword();
    }

    @Override
    public String getUsername(){
        return memberE.getUserid();
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    };

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    };

    @Override
    public boolean isEnabled(){
        memberE.getUpdateAt();
        return true;
    };

    @Override
    public String getName() {
        return null;
    }

}
