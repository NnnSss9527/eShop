package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.Order;

public interface OrderService {
	public void insertOrder(Order order);
	public List<Order> listOrder();
}
