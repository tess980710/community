package com.example.community.controller;

import com.example.community.service.MemberService;
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
    public String registerForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "/member/insertForm";
    }

    @PostMapping("/insertForm")
    public String register(@ModelAttribute @Valid MemberDto memberDto, BindingResult result) {

        if (result.hasErrors()) {
            return "member/insertForm";
        }

        memberService.save(memberDto);
        return "/member/loginForm";
    }
}
