package com.example.community.controller;

import javax.swing.text.html.Option;
import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        //java Optional 값이 있을 수도, 없을 수도 있음 명확하게 표현해주기 위한 클래스
        //NullpointerException을 방지하기 위함

        String name = "종민";
        if(name != null) {
            System.out.println(name.length());
        }
        //Optional<String> 문자가 있을 수도 있고 없을 수도 있다.
        //Optional.of는 null이 오면 안된다 null이 오면 시스템이 죽음
        Optional<String> name2 = Optional.of("종민");
        name2.ifPresent(n -> System.out.println(n.length()));

        //ofNullable
        //null이여도 예외 처리가 안남
        String test = null;
        Optional<String> name3 = Optional.ofNullable(null);

        //of
        //값이 있으면 실행
        Optional<String> name4 = Optional.of("값이 있을때 실행");
        name4.ifPresent(n -> System.out.println(name4+"안녕하세요"));

        //값이 없으면 기본값을 사용
        String result = (String)Optional.ofNullable(null).orElse("기본값 사용");
        System.out.println(result);

        //isPresent
        // 값이 있는지 없는지 boolean으로 판정하는 방법
        Optional<String> name5 = Optional.ofNullable("종민");

        if(name5.isPresent()) {
            System.out.println("이름이 있습니다.");
        }
    }
}
