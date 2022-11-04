package com.example.core.strategy.discount.impl;

import com.example.core.model.Member;
import com.example.core.strategy.discount.DiscountPolicy;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy {
    public final static int DISCOUNT_FIX_AMOUNT = 1000;

    @Override
    public int discount(Member member, int price) {
        switch (member.getGrade()) {
            case VIP:
                return DISCOUNT_FIX_AMOUNT;
            case BASIC:
            default:
                return 0;
        }
    }
}
