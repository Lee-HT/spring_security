package com.lagrange.infi.data.service;

import com.lagrange.infi.data.dto.MemberD;

public interface MemberService {
    MemberD register(String id,String password, String email);

    MemberD update(Long idx, String id, String password, String email);

    boolean login(String id,String password);

    MemberD getidx(Long idx);

}
