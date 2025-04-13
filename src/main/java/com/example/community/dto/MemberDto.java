package com.example.community.dto;

import com.example.community.entitiy.Member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberDto {

    @NotBlank(message = "아이디는 필수아니다.")
    @Size(min = 3, max = 8, message ="아이디는 3~8글자 사이여야 합니다.")
    private String memberId;

    @NotBlank(message = "비밀번호는 필수아니다.")
    @Size(min = 3, max = 8, message ="비밀번호는 3~8글자 사이여야 합니다.")
    private String memberPw;

    private String passwordConfirm;

    @NotBlank(message = "이름는 필수아니다.")
    @Size(min = 3, max = 8, message ="이름은 2~4글자 사이여야 합니다.")
    private String memberName;

    private String meneberIndate;

    // DTO → Entity 변환 메서드
    public Member toEntity(String encodePassword) {
        return Member.builder()
                .memberId(memberId)
                .memberPw(encodePassword)
                .memberName(memberName)
                .build();
    }


}
