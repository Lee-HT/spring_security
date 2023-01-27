package com.lagrange.infi.data.service;

public interface MemberService {
    void register(String id,String password);

    boolean login(String id,String password);

}
