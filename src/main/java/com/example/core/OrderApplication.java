package com.example.core;

import com.example.core.config.AppConfig;
import com.example.core.model.Grade;
import com.example.core.model.Member;
import com.example.core.model.Order;
import com.example.core.service.MemberService;
import com.example.core.service.OrderService;

public class OrderApplication {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        // MemberService
        MemberService memberService = appConfig.memberService();

        memberService.join(new Member(1L, "rolroralra", Grade.VIP));

        Member findMember = memberService.findMember(1L);

        System.out.println(findMember);

        // OrderService
        OrderService orderService = appConfig.orderService();

        Order order = orderService.createOrder(1L, "item01", 10000);

        System.out.println(order);

    }

}
