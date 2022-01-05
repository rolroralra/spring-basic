package com.example.demo.service.impl;

import com.example.demo.model.Member;
import com.example.demo.model.Order;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.impl.MemoryMemberRepository;
import com.example.demo.service.OrderService;
import com.example.demo.strategy.discount.DiscountPolicy;
import com.example.demo.strategy.discount.impl.FixDiscountPolicy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
