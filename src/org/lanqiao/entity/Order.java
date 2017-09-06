package org.lanqiao.entity;

import java.util.Date;

public class Order {
	private String orderid;
	private String userid;
	private double totalprice;
	private Date orderdate;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(String orderid, String userid, double totalprice, Date orderdate) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.totalprice = totalprice;
		this.orderdate = orderdate;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
}