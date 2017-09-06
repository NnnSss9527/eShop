package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.OrderDetail;

public interface OrderDetailService {
	public void insertOrderDetail(OrderDetail orderDetail);
	public List<OrderDetail> lisTOrderDetail(String orderid);
}
