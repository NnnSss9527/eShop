package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.UserState;

public interface UserStateDao {
	public List<UserState> list();
	public List<UserState> listUserState();
	public String update(String userid,String ustateid);
}
