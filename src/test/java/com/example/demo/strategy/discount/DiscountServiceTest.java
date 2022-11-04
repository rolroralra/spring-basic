package com.example.demo.strategy.discount;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.config.AutoAppConfig;
import com.example.demo.model.Grade;
import com.example.demo.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class DiscountServiceTest {
    @Test
    void test() {
        ApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        DiscountService discountService = applicationContext.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);

        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService)
            .isNotNull()
            .isInstanceOf(DiscountService.class);

        assertThat(discountPrice).isEqualTo(1000);
    }
}
