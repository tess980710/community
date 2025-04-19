package com.example.community.repository;


import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.community.entitiy.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByMemberId(String memberId); // 중복 체크

    Optional<Member> findByMemberId(String memberId);
}
