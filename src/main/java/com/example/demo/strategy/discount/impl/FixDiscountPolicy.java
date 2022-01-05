package com.example.demo.strategy.discount.impl;

import com.example.demo.model.Member;
import com.example.demo.strategy.discount.DiscountPolicy;

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
