package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.User;

public interface UserService {
	public String insertUser(User user);
	public User getUserById(String uloginid,String upassword);
	public User getUserByLoginId(String uloginid);
	public User getUserByUEmail(String uemail);
	public String updateUser(User user);
	public List<User> getUserLists();
	public String removeUserByUserId(String userid);
	public List<User> getLikeUserList(String inputs);
	public User getUserByUserid(String userid);
	public String updateUemail(User user);
}
