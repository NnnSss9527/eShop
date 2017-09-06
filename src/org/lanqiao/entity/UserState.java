package org.lanqiao.entity;

public class UserState {
	private String uloginid;
	private String userid;
	private String ustateid;
	private String ustatename;
	public UserState() {
		super();
	}
	public UserState(String ustateid, String ustatename) {
		super();
		this.ustateid = ustateid;
		this.ustatename = ustatename;
	}
	public UserState(String uloginid, String userid, String ustateid, String ustatename) {
		super();
		this.uloginid = uloginid;
		this.userid = userid;
		this.ustateid = ustateid;
		this.ustatename = ustatename;
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
	public String getUstateid() {
		return ustateid;
	}
	public void setUstateid(String ustateid) {
		this.ustateid = ustateid;
	}
	public String getUstatename() {
		return ustatename;
	}
	public void setUstatename(String ustatename) {
		this.ustatename = ustatename;
	}
}