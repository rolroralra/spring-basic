package com.example.demo.config;

import com.example.demo.repository.impl.MemoryMemberRepository;
import com.example.demo.service.MemberService;
import com.example.demo.service.OrderService;
import com.example.demo.service.impl.MemberServiceImpl;
import com.example.demo.service.impl.OrderServiceImpl;
import com.example.demo.strategy.discount.DiscountPolicy;
import com.example.demo.strategy.discount.impl.FixDiscountPolicy;
import com.example.demo.strategy.discount.impl.RateDiscountPolicy;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
