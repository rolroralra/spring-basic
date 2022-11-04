package com.example.core.strategy.discount;

import com.example.core.model.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
