package com.lagrange.infi.data.service;

import com.lagrange.infi.data.dto.Members;

public interface MemberService {
    Members register(String id,String password);

    boolean login(String id,String password);

}
