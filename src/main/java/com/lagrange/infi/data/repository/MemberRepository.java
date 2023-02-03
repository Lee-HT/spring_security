package com.lagrange.infi.data.repository;

import com.lagrange.infi.data.entity.MemberE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberE,Long> {
    MemberE findByid(Long id);
    MemberE findByUserid(String userid);
    boolean existsByUserid(String id);
    void deleteByUserid(String userid);

}
