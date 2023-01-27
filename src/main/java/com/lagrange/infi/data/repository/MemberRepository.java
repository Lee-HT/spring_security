package com.lagrange.infi.data.repository;

import com.lagrange.infi.data.dto.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members,Long> {

}
