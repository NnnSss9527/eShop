package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.UserState;

public interface UserStateService {
	public List<UserState> userStateList();
	public List<UserState> getListUserState();
	public String updateUser(String userid,String ustateid);
}
