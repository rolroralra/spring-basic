package com.example.core.strategy.discount;

import com.example.core.model.Member;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscountService {

    private final Map<String, DiscountPolicy> policyMap;

    private final List<DiscountPolicy> policies;

    public int discount(Member member, int price, String discountCode) {
        DiscountPolicy discountPolicy = policyMap.get(discountCode);

        System.out.println(policies);
        System.out.println("discountCode = " + discountCode);
        System.out.println("discountPolicy = " + discountPolicy);

        return discountPolicy.discount(member, price);
    }
}
