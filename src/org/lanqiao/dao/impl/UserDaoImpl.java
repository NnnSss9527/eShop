package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.UserDao;
import org.lanqiao.entity.User;
import org.lanqiao.util.DBUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public String insert(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into t_user values(?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserid());
			ps.setString(2, user.getUemail());
			ps.setString(3, user.getUloginid());
			ps.setString(4, user.getUpassword());
			ps.setString(5, user.getUsex());
			ps.setString(6, user.getUaddress());
			ps.setString(7, user.getUtel());
			ps.setString(8, user.getUstateid());
			ps.setString(9, user.getUroleid());
			ps.setString(10, user.getUemailstateid());
			ps.executeUpdate();
		} catch (SQLException e) {
			return "0";
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "1";
	}

	@Override
	public User getUser(String uloginid) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_user where uloginid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uloginid);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"), rs.getString("uemailstateid"));
			}
		} catch (Exception e) {
		} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return user;
	}

	@Override
	public String update(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update t_user set uemail = ?,uloginid = ?,upassword = ?,usex = ?,uaddress = ?,utel = ?,ustateid = ?,uroleid = ? where userid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUemail());
			ps.setString(2, user.getUloginid());
			ps.setString(3, user.getUpassword());
			ps.setString(4, user.getUsex());
			ps.setString(5, user.getUaddress());
			ps.setString(6, user.getUtel());
			ps.setString(7, user.getUstateid());
			ps.setString(8, user.getUroleid());
			ps.setString(9, user.getUserid());
			ps.executeUpdate();
		} catch (SQLException e) {
			return "0";
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "1";
	}

	@Override
	public User getUserE(String uemail) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_user where uemail = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uemail);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"));
			}
		} catch (Exception e) {
		} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return user;
	}

	@Override
	public List<User> getUserList() {
		List<User> list = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_user";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			User user = null;
			while (rs.next()) {
				user = new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"), rs.getString("uemailstateid"));
				list.add(user);
			}
		} catch (Exception e) {
		} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	@Override
	public String remove(String userid) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from t_user where userid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,userid);
			ps.executeUpdate();
		} catch (SQLException e) {
			return "0";
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "1";
	}

	@Override
	public List<User> getLikeUserList(String inputs) {
		List<User> list = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_user where userid like ? or uemail like ? or uloginid like ? or upassword like ? or usex like ? or utel like ? or ustateid like ? or uroleid like ?";
			ps = conn.prepareStatement(sql);
			for (int i = 1; i < 9; i++) {
				ps.setString(i, "%"+inputs+"%");
			}
			rs = ps.executeQuery();
			
			User user = null;
			while (rs.next()) {
				user = new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"));
				list.add(user);
			}
		} catch (Exception e) {
		} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	@Override
	public User getUserByUserid(String userid) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_user where userid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"), rs.getString("uemailstateid"));
			}
		} catch (Exception e) {
		} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		System.out.println(user.getUemailstateid());
		return user;
	}

	@Override
	public String updateUemail(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update t_user set uemailstateid = ? where userid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "5925df55-a830-47f6-9409-75979b508d5d");
			ps.setString(2, user.getUserid());
			ps.executeUpdate();
		} catch (SQLException e) {
			return "0";
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "1";
	}

}
