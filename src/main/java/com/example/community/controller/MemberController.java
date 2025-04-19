package com.example.community.controller;

import com.example.community.dto.LoginDto;
import com.example.community.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.community.dto.MemberDto;
import com.example.community.repository.MemberRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/insertForm")
    public String insertForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/insertForm";
    }

    @PostMapping("/insert")
    public String register(@ModelAttribute @Valid MemberDto memberDto, BindingResult result, HttpSession session) {

        if (result.hasErrors()) {
            return "member/insertForm";
        }
        System.out.println(memberDto);
        //회원가입 저장
        memberService.save(memberDto);

        LoginDto loginDto = new LoginDto(memberDto.getMemberId(),memberDto.getMemberPw());
        session.setAttribute("loginDto",loginDto);

        return "redirect:/";
    }


    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "member/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, Model model, HttpSession session){
        boolean success = memberService.login(loginDto);

        if (success) {
            session.setAttribute("loginDto", loginDto); // 로그인 성공시 세션에 저장
            return "redirect:/"; //로그인 성공 -> 메인 페이지로 이동
        }else{
            model.addAttribute("loginDto", loginDto);
            model.addAttribute("error","아이디 또는 비밀번호가 틀렸습니다.");
            return "member/loginForm"; //로그인 실패시 로그인 페이지 이동
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); //로그아웃 버튼을 누르면 세션 삭제
        return "redirect:/";
    }
}
