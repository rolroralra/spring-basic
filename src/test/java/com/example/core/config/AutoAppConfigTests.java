package com.example.core.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.core.repository.MemberRepository;
import com.example.core.service.MemberService;
import com.example.core.service.OrderService;
import com.example.core.service.impl.MemberServiceImpl;
import com.example.core.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class AutoAppConfigTests {

    @Test
    void configurationTest() {
        ApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = applicationContext.getBean(MemberService.class);
        OrderService orderService = applicationContext.getBean(OrderService.class);

        MemberRepository memberRepository = applicationContext.getBean(MemberRepository.class);

        assertThat(memberService).isNotNull();
        assertThat(orderService).isNotNull();
        assertThat(memberRepository).isNotNull();

        assertThat(memberService)
            .isInstanceOf(MemberServiceImpl.class);
        assertThat(((MemberServiceImpl)memberService).getMemberRepository())
            .isEqualTo(memberRepository);

        assertThat(orderService)
            .isInstanceOf(OrderServiceImpl.class);
        assertThat(((OrderServiceImpl)orderService).getMemberRepository())
            .isEqualTo(memberRepository);

        System.out.println("memberService = " + memberService);
        System.out.println("orderService = " + orderService);
        System.out.println("memberRepository = " + memberRepository);
    }

}
