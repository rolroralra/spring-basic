package com.example.demo;

import com.example.demo.config.AppConfig;
import com.example.demo.model.Grade;
import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberSpringApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // MemberService
        MemberService memberService = applicationContext.getBean(MemberService.class);

        memberService.join(new Member(1L, "rolroralra", Grade.VIP));

        Member findMember = memberService.findMember(1L);

        System.out.println(findMember);
    }
}
