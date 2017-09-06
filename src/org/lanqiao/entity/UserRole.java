package org.lanqiao.entity;

public class UserRole {
	private String uloginid;
	private String userid;
	private String uroleid;
	private String urolename;
	public UserRole() {
		super();
	}
	public UserRole(String uroleid, String urolename) {
		super();
		this.uroleid = uroleid;
		this.urolename = urolename;
	}
	public UserRole(String uloginid, String userid, String uroleid, String urolename) {
		super();
		this.uloginid = uloginid;
		this.userid = userid;
		this.uroleid = uroleid;
		this.urolename = urolename;
	}
	public String getUloginid() {
		return uloginid;
	}
	public void setUloginid(String uloginid) {
		this.uloginid = uloginid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String geturoleid() {
		return uroleid;
	}
	public void seturoleid(String uroleid) {
		this.uroleid = uroleid;
	}
	public String geturolename() {
		return urolename;
	}
	public void seturolename(String urolename) {
		this.urolename = urolename;
	}
}
