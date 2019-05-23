package com.jt.service;

import com.jt.pojo.Order;

/**
 * Created by CGB on 2019/4/23.
 */
public interface OrderService {
    String saveOrder(Order order);

    Order findOrderById(String id);
}
