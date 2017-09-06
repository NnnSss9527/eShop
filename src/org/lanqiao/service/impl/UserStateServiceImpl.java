package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.UserStateDao;
import org.lanqiao.dao.impl.UserStateDaoImpl;
import org.lanqiao.entity.UserState;
import org.lanqiao.service.UserStateService;

public class UserStateServiceImpl implements UserStateService {
	UserStateDao dao = new UserStateDaoImpl();
	@Override
	public List<UserState> userStateList() {
		return dao.list();
	}

	@Override
	public String updateUser(String userid, String ustateid) {
		return dao.update(userid, ustateid);
	}

	@Override
	public List<UserState> getListUserState() {
		return dao.listUserState();
	}

}
