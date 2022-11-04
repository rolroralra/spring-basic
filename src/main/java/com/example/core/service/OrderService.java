package com.example.core.service;

import com.example.core.model.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
