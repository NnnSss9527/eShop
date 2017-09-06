package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.UserRoleDao;
import org.lanqiao.entity.UserRole;
import org.lanqiao.util.DBUtil;

public class UserRoleDaoImpl implements UserRoleDao {

	@Override
	public List<UserRole> list() {
		List<UserRole> list = new ArrayList<UserRole>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select uloginid,userid,t1.uroleid,urolename from T_USER t1,T_USERROLE t2 where t1.UROLEID = t2.UROLEID";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			UserRole userRole = null;
			while (rs.next()) {
				userRole = new UserRole(rs.getString("uloginid"), rs.getString("userid"), rs.getString("uroleid"), rs.getString("urolename"));
				list.add(userRole);
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
	public List<UserRole> listUserRole() {
		List<UserRole> list = new ArrayList<UserRole>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from T_USERROLE";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			UserRole userRole = null;
			while (rs.next()) {
				userRole = new UserRole(rs.getString("uroleid"), rs.getString("urolename"));
				list.add(userRole);
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
	public String update(String userid, String uroleid) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update t_user set uroleid = ? where userid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uroleid);
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

}
