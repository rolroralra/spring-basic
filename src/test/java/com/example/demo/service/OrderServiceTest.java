package com.example.demo.service;

import com.example.demo.model.Grade;
import com.example.demo.model.Member;
import com.example.demo.model.Order;
import com.example.demo.service.impl.MemberServiceImpl;
import com.example.demo.service.impl.OrderServiceImpl;
import com.example.demo.strategy.discount.impl.FixDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @org.junit.jupiter.api.Order(1)
    @DisplayName("Test createOrder by VIP member")
    @ParameterizedTest
    @MethodSource("com.example.demo.service.MemberServiceTest#provideVipMember")
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
    @MethodSource("com.example.demo.service.MemberServiceTest#provideBasicMember")
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