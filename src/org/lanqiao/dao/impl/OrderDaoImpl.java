package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.OrderDao;
import org.lanqiao.entity.Order;
import org.lanqiao.util.DBUtil;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void insert(Order order) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into t_order values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, order.getOrderid());
			ps.setString(2, order.getUserid());
			ps.setDouble(3, order.getTotalprice());
			ps.setTimestamp(4, new Timestamp(order.getOrderdate().getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
	}

	@Override
	public List<Order> list() {
		List<Order> list = new ArrayList<Order>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from t_order";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			Order order = null;
			while (rs.next()) {
				order = new Order(rs.getString("orderid"), rs.getString("userid"), rs.getDouble("totalprice"), rs.getTimestamp("orderdate"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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