package com.example.core.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.core.repository.MemberRepository;
import com.example.core.service.impl.MemberServiceImpl;
import com.example.core.service.impl.OrderServiceImpl;
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

    @Test
    void configurationDeep() {
        ApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = applicationContext.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
