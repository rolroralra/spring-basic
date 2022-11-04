package com.example.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.example.core.strategy.discount.DiscountPolicy;
import com.example.core.strategy.discount.impl.FixDiscountPolicy;
import com.example.core.strategy.discount.impl.RateDiscountPolicy;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextExtendsBeanFindTests {

    @Configuration
    static class ExtendsBeanConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

    private final ApplicationContext applicationContext
        = new AnnotationConfigApplicationContext(ExtendsBeanConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, NoUniqueBeanDefinitionException 발생")
    void findBeanByParentTypeThrownNoUniqueBeanDefinitionException() {
        assertThatExceptionOfType(NoUniqueBeanDefinitionException.class)
            .isThrownBy(() -> applicationContext.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, Bean 이름으로 지정하면 된다.")
    void findBeanByNameAndParentType() {
        DiscountPolicy bean
            = applicationContext.getBean("fixDiscountPolicy", DiscountPolicy.class);

        assertThat(bean).isInstanceOf(DiscountPolicy.class);
        assertThat(bean).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        RateDiscountPolicy bean = applicationContext.getBean(RateDiscountPolicy.class);

        assertThat(bean).isInstanceOf(DiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllBeansByParentType() {
        Map<String, DiscountPolicy> beansOfType
            = applicationContext.getBeansOfType(DiscountPolicy.class);

        for (Entry<String, DiscountPolicy> entry : beansOfType.entrySet()) {
            System.out.println("entry = " + entry);

            assertThat(entry.getValue()).isInstanceOf(DiscountPolicy.class);
        }

        assertThat(beansOfType).hasSize(2);
    }

    @Test
    @DisplayName("Object 타입으로 모든 Bean 조회")
    void findAllBeansByObjectType() {
        Map<String, Object> beansOfType
            = applicationContext.getBeansOfType(Object.class);

        for (Entry<String, Object> entry : beansOfType.entrySet()) {
            System.out.println("entry = " + entry);
        }

        System.out.println(beansOfType.size());
    }
}
