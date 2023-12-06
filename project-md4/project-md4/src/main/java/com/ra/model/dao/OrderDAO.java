package com.ra.model.dao;

import com.ra.model.entity.Category;
import com.ra.model.entity.Order;

import java.util.List;

public interface OrderDAO {
    Boolean createOrder(Order order);
    List<Order> allOrder();
}
