package org.lanqiao.entity;

public class OrderDetail {
	private String orderdetailid;
	private String gtitle;
	private double ginprice;
	private int gnumber;
	private String orderid;
	private String gid;
	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetail(String orderdetailid, String gtitle, double ginprice, int gnumber, String orderid, String gid) {
		super();
		this.orderdetailid = orderdetailid;
		this.gtitle = gtitle;
		this.ginprice = ginprice;
		this.gnumber = gnumber;
		this.orderid = orderid;
		this.gid = gid;
	}
	public String getOrderdetailid() {
		return orderdetailid;
	}
	public void setOrderdetailid(String orderdetailid) {
		this.orderdetailid = orderdetailid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public double getGinprice() {
		return ginprice;
	}
	public void setGinprice(double ginprice) {
		this.ginprice = ginprice;
	}
	public int getGnumber() {
		return gnumber;
	}
	public void setGnumber(int gnumber) {
		this.gnumber = gnumber;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
}