package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.OrderDao;
import org.lanqiao.dao.impl.OrderDaoImpl;
import org.lanqiao.entity.Order;
import org.lanqiao.service.OrderService;

public class OrderServiceImpl implements OrderService {
	OrderDao dao = new OrderDaoImpl();
	@Override
	public void insertOrder(Order order) {
		dao.insert(order);
	}
	@Override
	public List<Order> listOrder() {
		return dao.list();
	}

}
