package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.UserRole;

public interface UserRoleService {
	public List<UserRole> UserRoleList();
	public List<UserRole> getListUserRole();
	public String updateUser(String userid,String uroleid);
}
