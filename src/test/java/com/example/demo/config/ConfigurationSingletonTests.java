package com.example.demo.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.repository.MemberRepository;
import com.example.demo.service.impl.MemberServiceImpl;
import com.example.demo.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ConfigurationSingletonTests {
    @Test
    void configurationTest() {
        ApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = applicationContext.getBean(MemberServiceImpl.class);
        OrderServiceImpl orderService = applicationContext.getBean(OrderServiceImpl.class);

        MemberRepository memberRepository = applicationContext.getBean(MemberRepository.class);

        assertThat(memberService.getMemberRepository()).isEqualTo(memberRepository);
        assertThat(orderService.getMemberRepository()).isEqualTo(memberRepository);

        System.out.println("memberRepository = " + memberRepository);
        System.out.println("memberService.getMemberRepository() = " + memberService.getMemberRepository());
        System.out.println("orderService.getMemberRepository() = " + orderService.getMemberRepository());
    }
}
