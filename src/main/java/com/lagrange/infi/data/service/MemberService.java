package com.lagrange.infi.data.service;

import com.lagrange.infi.data.dto.MemberD;
import com.lagrange.infi.data.entity.MemberE;

public interface MemberService {
    MemberD register(String userid,String password, String email);

    boolean unregister(String userid,String password);

    MemberD update(Long id, String userid, String password, String email, String newPassword);

    boolean login(String userid,String password);

    void jwtlogin(MemberD memberD);

    MemberD getId(Long id);

}
