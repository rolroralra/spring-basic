package com.example.core;

import com.example.core.config.AppConfig;
import com.example.core.model.Grade;
import com.example.core.model.Member;
import com.example.core.model.Order;
import com.example.core.service.MemberService;
import com.example.core.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderSpringApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // MemberService
        MemberService memberService = applicationContext.getBean(MemberService.class);

        memberService.join(new Member(1L, "rolroralra", Grade.VIP));

        Member findMember = memberService.findMember(1L);

        System.out.println(findMember);

        // OrderService
        OrderService orderService = applicationContext.getBean(OrderService.class);

        Order order = orderService.createOrder(1L, "item01", 10000);

        System.out.println(order);

    }

}
