package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.UserRoleDao;
import org.lanqiao.dao.impl.UserRoleDaoImpl;
import org.lanqiao.entity.UserRole;
import org.lanqiao.service.UserRoleService;

public class UserRoleServiceImpl implements UserRoleService {
	UserRoleDao dao = new UserRoleDaoImpl();
	@Override
	public List<UserRole> UserRoleList() {
		return dao.list();
	}

	@Override
	public List<UserRole> getListUserRole() {
		return dao.listUserRole();
	}

	@Override
	public String updateUser(String userid, String uroleid) {
		return dao.update(userid, uroleid);
	}

}
