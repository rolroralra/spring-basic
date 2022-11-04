package com.example.core.service;

import com.example.core.config.AppConfig;
import com.example.core.model.Grade;
import com.example.core.model.Member;
import com.example.core.model.Order;
import com.example.core.strategy.discount.impl.FixDiscountPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @org.junit.jupiter.api.Order(1)
    @DisplayName("Test createOrder by VIP member")
    @ParameterizedTest
    @MethodSource("com.example.core.service.MemberServiceTest#provideVipMember")
    void createOrderByVipMember(Member vipMember) {
        // Given
        assertThat(vipMember).isNotNull()
                        .hasFieldOrPropertyWithValue("grade", Grade.VIP);
        memberService.join(vipMember);

        // When
        Order order = orderService.createOrder(vipMember.getId(), "itemA", 10000);

        // Then
        assertThat(order).isNotNull();
        assertThat(order.getDiscountPrice()).isEqualTo(FixDiscountPolicy.DISCOUNT_FIX_AMOUNT);
    }

    @org.junit.jupiter.api.Order(2)
    @DisplayName("Test createOrder by basic member")
    @ParameterizedTest
    @MethodSource("com.example.core.service.MemberServiceTest#provideBasicMember")
    void createOrderByBasicMember(Member basicMember) {
        // Given
        assertThat(basicMember).isNotNull()
                .hasFieldOrPropertyWithValue("grade", Grade.BASIC);
        memberService.join(basicMember);

        // When
        Order order = orderService.createOrder(basicMember.getId(), "itemA", 10000);

        // Then
        assertThat(order).isNotNull();
        assertThat(order.getDiscountPrice()).isEqualTo(0);
    }
}
