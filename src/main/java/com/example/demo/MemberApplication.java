package com.example.demo;

import com.example.demo.config.AppConfig;
import com.example.demo.model.Grade;
import com.example.demo.model.Member;
import com.example.demo.service.MemberService;

public class MemberApplication {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        // MemberService
        MemberService memberService = appConfig.memberService();

        memberService.join(new Member(1L, "rolroralra", Grade.VIP));

        Member findMember = memberService.findMember(1L);

        System.out.println(findMember);
    }
}
