package com.example.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.example.core.config.AppConfig;
import com.example.core.service.MemberService;
import com.example.core.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextBeanFindTests {

    private final ApplicationContext applicationContext
        = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("getBean by bean name")
    void findBeanByName() {
        Object memberService = applicationContext.getBean("memberService");

        assertThat(memberService)
            .isInstanceOf(MemberService.class)
            .isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("getBean by bean name and type")
    void findBeanByNameAndType() {
        MemberService memberService
            = applicationContext.getBean("memberService", MemberService.class);

        assertThat(memberService)
            .isInstanceOf(MemberService.class)
            .isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("getBean by bean type")
    void findBeanByType() {
        MemberService memberService
            = applicationContext.getBean(MemberService.class);

        assertThat(memberService)
            .isInstanceOf(MemberService.class)
            .isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("등록되지 않은 Bean을 조회할 시, NoSuchBeanDefinitionException 발생")
    void testNoSuchBeanDefinitionException() {
        assertThatExceptionOfType(NoSuchBeanDefinitionException.class)
            .isThrownBy(() -> applicationContext.getBean("xxxBean"));

    }
}
