package com.jt.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;

	/**
	 * 分析:
	 * 	用戶提交一次訂單,入庫三張表
	 *
	 * @param order
	 * @return
	 */
	@Override
	@Transactional
	public String saveOrder(Order order) {
		Date date = new Date();
		//1.準備orderId
		String orderId=""+order.getUserId()+System.currentTimeMillis();
		System.out.println(orderId);
		order.setOrderId(orderId).setStatus(1).setCreated(date).setUpdated(date);
		orderMapper.insert(order);
		System.out.println("訂單入庫成功");


		//入庫訂單物流信息
		OrderShipping shipping = order.getOrderShipping();
		shipping.setOrderId(orderId).setCreated(date).setUpdated(date);
		orderShippingMapper.insert(shipping);
		System.out.println("訂單物流入庫成功");

		//3.入庫訂單商品
		List<OrderItem> items = order.getOrderItems();
		for (OrderItem item : items) {
			item.setOrderId(orderId).setCreated(date).setUpdated(date);
			orderItemMapper.insert(item);
		}
		return orderId;
	}

	@Override
	public Order findOrderById(String id) {
		Order order = orderMapper.selectById(id);
		OrderShipping orderShipping = orderShippingMapper.selectById(id);
		QueryWrapper<OrderItem> wrapper=new QueryWrapper<>();
		wrapper.eq("order_id",id);
		List<OrderItem> items = orderItemMapper.selectList(wrapper);
		order.setOrderShipping(orderShipping).setOrderItems(items);

		return order;
	}
}