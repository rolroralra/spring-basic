package com.example.core.service.impl;

import com.example.core.model.Member;
import com.example.core.model.Order;
import com.example.core.repository.MemberRepository;
import com.example.core.service.OrderService;
import com.example.core.strategy.discount.DiscountPolicy;
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
