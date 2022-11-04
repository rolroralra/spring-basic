package com.example.demo.service.impl;

import com.example.demo.model.Member;
import com.example.demo.model.Order;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.OrderService;
import com.example.demo.strategy.discount.DiscountPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // TODO: Deprecation. For just test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
