package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.UserStateDao;
import org.lanqiao.entity.UserState;
import org.lanqiao.util.DBUtil;

public class UserStateDaoImpl implements UserStateDao {

	@Override
	public List<UserState> list() {
		List<UserState> list = new ArrayList<UserState>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select uloginid,userid,t1.ustateid,ustatename from T_USER t1,T_USERSTATE t2 where t1.USTATEID = t2.USTATEID";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			UserState userState = null;
			while (rs.next()) {
				userState = new UserState(rs.getString("uloginid"), rs.getString("userid"), rs.getString("ustateid"), rs.getString("ustatename"));
				list.add(userState);
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
	public String update(String userid, String ustateid) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update t_user set ustateid = ? where userid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, ustateid);
			ps.setString(2, userid);
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
	public List<UserState> listUserState() {
		List<UserState> list = new ArrayList<UserState>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from T_USERSTATE";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			UserState userState = null;
			while (rs.next()) {
				userState = new UserState(rs.getString("ustateid"), rs.getString("ustatename"));
				list.add(userState);
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

}
