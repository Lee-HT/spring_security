package com.lagrange.infi.data.service;

import com.lagrange.infi.data.dto.MemberD;
import com.lagrange.infi.data.entity.MemberE;

public interface MemberService {
    MemberD register(String id,String password);

    MemberD update(Long idx, String id, String password);

    boolean login(String id,String password);

}
