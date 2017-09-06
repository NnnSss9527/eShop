package org.lanqiao.entity;

public class CartCookie {
	private String gid;
	private int count;
	public CartCookie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartCookie(String gid, int count) {
		super();
		this.gid = gid;
		this.count = count;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}