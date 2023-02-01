package com.lagrange.infi.data.repository;

import com.lagrange.infi.data.entity.MemberE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberE,Long> {
    MemberE findByIdx(Long id);
    MemberE findById(String id);

    boolean existsById(String id);

}
