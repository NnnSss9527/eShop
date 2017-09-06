package org.lanqiao.entity;

public class CartGoods {
	private String gid;
	private String gtitle;
	private double gsaleprice;
	private double ginprice;
	private int count;
	public CartGoods() {
		super();
	}
	public CartGoods(String gid, String gtitle, double gsaleprice, double ginprice, int count) {
		super();
		this.gid = gid;
		this.gtitle = gtitle;
		this.gsaleprice = gsaleprice;
		this.ginprice = ginprice;
		this.count = count;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public double getGsaleprice() {
		return gsaleprice;
	}
	public void setGsaleprice(double gsaleprice) {
		this.gsaleprice = gsaleprice;
	}
	public double getGinprice() {
		return ginprice;
	}
	public void setGinprice(double ginprice) {
		this.ginprice = ginprice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}