package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.UserRole;

public interface UserRoleDao {
	public List<UserRole> list();
	public List<UserRole> listUserRole();
	public String update(String userid,String uroleid);
}
