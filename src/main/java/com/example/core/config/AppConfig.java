package com.example.core.config;

import com.example.core.repository.impl.MemoryMemberRepository;
import com.example.core.service.MemberService;
import com.example.core.service.OrderService;
import com.example.core.service.impl.MemberServiceImpl;
import com.example.core.service.impl.OrderServiceImpl;
import com.example.core.strategy.discount.DiscountPolicy;
import com.example.core.strategy.discount.impl.RateDiscountPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call " + this.getClass().getSimpleName() + ".memberService()");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call " + this.getClass().getSimpleName() + ".orderService()");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call " + this.getClass().getSimpleName() + ".memberRepository()");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call " + this.getClass().getSimpleName() + ".discountPolicy()");

//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
