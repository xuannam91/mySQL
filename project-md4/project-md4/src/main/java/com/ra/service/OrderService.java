package com.ra.service;

import com.ra.model.entity.Order;

import java.util.List;

public interface OrderService {
    Boolean createOrder(Order order);
    List<Order> allOrder();
}
