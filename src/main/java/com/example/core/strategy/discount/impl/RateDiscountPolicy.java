package com.example.core.strategy.discount.impl;

import com.example.core.model.Member;
import com.example.core.strategy.discount.DiscountPolicy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy {
    public static int DISCOUNT_PERCENT = 10;

    @Override
    public int discount(Member member, int price) {
        switch (member.getGrade()) {
            case VIP:
                return price * DISCOUNT_PERCENT / 100;
            case BASIC:
            default:
                return 0;
        }
    }
}
