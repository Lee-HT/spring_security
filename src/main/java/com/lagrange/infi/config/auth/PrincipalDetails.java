package com.lagrange.infi.config.auth;

import com.lagrange.infi.data.entity.MemberE;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PrincipalDetails implements UserDetails {

    private MemberE memberE;

    public PrincipalDetails(MemberE memberE){
        this.memberE = memberE;
    }

    // 해당 유저의 권한 return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return memberE.getRole();
            }
        });
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

}
