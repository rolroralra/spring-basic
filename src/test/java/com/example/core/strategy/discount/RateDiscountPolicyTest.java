package com.example.core.strategy.discount;

import com.example.core.model.Grade;
import com.example.core.model.Member;
import com.example.core.strategy.discount.impl.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @ParameterizedTest
    @ValueSource(ints = {10000, 20000, 30000})
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void test_discount_percentage_by_vip_member(int price) {
        // Given
        Member vipMember = new Member(1L, "memberVIP", Grade.VIP);

        // When
        int discount = discountPolicy.discount(vipMember, price);

        // Then
        assertThat(discount).isEqualTo(price * RateDiscountPolicy.DISCOUNT_PERCENT / 100);
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 20000, 30000})
    @DisplayName("BASIC은 10% 할인이 적용되지 않는다.")
    void test_discount_percentage_by_basic_member(int price) {
        // Given
        Member basicMember = new Member(1L, "memberBasic", Grade.BASIC);

        // When
        int discount = discountPolicy.discount(basicMember, price);

        // Then
        assertThat(discount).isEqualTo(0);
    }
}
