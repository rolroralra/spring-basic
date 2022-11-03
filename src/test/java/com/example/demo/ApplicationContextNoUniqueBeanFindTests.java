package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.impl.MemoryMemberRepository;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextNoUniqueBeanFindTests {

    @Configuration
    static class NoUniqueBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
    private final ApplicationContext applicationContext
        = new AnnotationConfigApplicationContext(NoUniqueBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, NoUniqueBeanDefinitionException 발생")
    void find() {
        assertThatExceptionOfType(NoUniqueBeanDefinitionException.class)
            .isThrownBy(() -> applicationContext.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, Bean 이름으로 지정하면 된다.")
    void findBeanByNameAndClass() {
        MemberRepository bean
            = applicationContext.getBean("memberRepository2", MemberRepository.class);

        assertThat(bean).isInstanceOf(MemberRepository.class);
        assertThat(bean).isInstanceOf(MemoryMemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입에 해당하는 모든 Bean 조회 하기")
    void findBeanByClass() {
        Map<String, MemberRepository> beansOfType
            = applicationContext.getBeansOfType(MemberRepository.class);

        for (Entry<String, MemberRepository> entry : beansOfType.entrySet()) {
            System.out.println("entry = " + entry);

            assertThat(entry.getValue()).isInstanceOf(MemberRepository.class);
            assertThat(entry.getValue()).isInstanceOf(MemoryMemberRepository.class);
        }

        assertThat(beansOfType).hasSize(2);
    }
}
