package com.example.community.service;

import com.example.community.dto.LoginDto;
import com.example.community.dto.MemberDto;
import com.example.community.entitiy.Member;
import com.example.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {



    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional //정상적 처리가 되면
    public void save(MemberDto memberDto) {
        if(memberRepository.existsByMemberId(memberDto.getMemberId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        String encodePW = passwordEncoder.encode(memberDto.getMemberPw()); //비밀번호 암호화
        Member member = memberDto.toEntity(encodePW); //비밀번호 암호화 entity로 보내기

        memberRepository.save(member); //맴버저장

    }

    public boolean login(LoginDto loginDto) {

        return memberRepository.findByMemberId(loginDto.getMemberId())
        .map(member -> passwordEncoder.matches(loginDto.getMemberPw(), member.getMemberPw()))
                .orElse(false);
    }
}
