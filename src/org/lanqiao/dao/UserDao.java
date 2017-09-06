package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.User;

public interface UserDao {
	public String insert(User user);
	public User getUser(String uloginid);
	public User getUserE(String uemail);
	public String update(User user);
	public List<User> getUserList();
	public String remove(String userid);
	public List<User> getLikeUserList(String inputs);
	public User getUserByUserid(String userid);
	public String updateUemail(User user);
}
