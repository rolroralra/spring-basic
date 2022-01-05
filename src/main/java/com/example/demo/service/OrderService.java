package com.example.demo.service;

import com.example.demo.model.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
