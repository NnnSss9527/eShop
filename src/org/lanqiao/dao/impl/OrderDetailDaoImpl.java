package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.OrderDetailDao;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.util.DBUtil;

public class OrderDetailDaoImpl implements OrderDetailDao {

	@Override
	public void insert(OrderDetail orderDetail) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into t_orderdetail values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderDetail.getOrderdetailid());
			ps.setString(2, orderDetail.getGtitle());
			ps.setDouble(3, orderDetail.getGinprice());
			ps.setInt(4, orderDetail.getGnumber());
			ps.setString(5, orderDetail.getOrderid());
			ps.setString(6, orderDetail.getGid());
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
	public List<OrderDetail> list(String orderid) {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from t_orderdetail where orderid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderid);
			
			rs = ps.executeQuery();
			
			OrderDetail orderdetail = null;
			while (rs.next()) {
				orderdetail = new OrderDetail(rs.getString("orderdetailid"), rs.getString("gtitle"), rs.getDouble("ginprice"), rs.getInt("gnumber"), rs.getString("orderid"), rs.getString("gid"));
				list.add(orderdetail);
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
