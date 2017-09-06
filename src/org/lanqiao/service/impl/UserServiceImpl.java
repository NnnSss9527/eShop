package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.UserDao;
import org.lanqiao.dao.impl.UserDaoImpl;
import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao dao = new UserDaoImpl();
	@Override
	public String insertUser(User user) {
		return dao.insert(user);
	}
	@Override
	public User getUserById(String uloginid, String upassword) {
		User user = dao.getUser(uloginid);
		if (user != null && !user.getUpassword().equals(upassword)) {
			user = null;
		}
		return user;
	}
	@Override
	public User getUserByLoginId(String uloginid) {
		return dao.getUser(uloginid);
	}
	@Override
	public String updateUser(User user) {
		return dao.update(user);
	}
	@Override
	public User getUserByUEmail(String uemail) {
		return dao.getUserE(uemail);
	}
	@Override
	public List<User> getUserLists() {
		return dao.getUserList();
	}
	@Override
	public String removeUserByUserId(String userid) {
		return dao.remove(userid);
	}
	@Override
	public List<User> getLikeUserList(String inputs) {
		return dao.getLikeUserList(inputs);
	}
	@Override
	public User getUserByUserid(String userid) {
		return dao.getUserByUserid(userid);
	}
	@Override
	public String updateUemail(User user) {
		return dao.updateUemail(user);
	}
}