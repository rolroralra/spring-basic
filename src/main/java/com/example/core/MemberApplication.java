package com.example.core;

import com.example.core.config.AppConfig;
import com.example.core.model.Grade;
import com.example.core.model.Member;
import com.example.core.service.MemberService;

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
