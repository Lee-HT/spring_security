package com.lagrange.infi.data.service;

import com.lagrange.infi.data.dto.MemberD;

public interface MemberService {
    MemberD register(String userid,String password, String email);

    MemberD update(Long id, String userid, String password, String email);

    boolean login(String userid,String password);

    MemberD getId(Long id);

}
