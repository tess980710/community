package com.example.community.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.community.entitiy.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByMemberId(String memberId); // 중복 체크

}
