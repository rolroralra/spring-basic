package com.example.demo.strategy.discount;

import com.example.demo.model.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
