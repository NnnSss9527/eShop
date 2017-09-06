package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.PublisherDao;
import org.lanqiao.entity.Publisher;
import org.lanqiao.service.GoodsService;
import org.lanqiao.service.impl.GoodsServiceImpl;
import org.lanqiao.util.DBUtil;

public class PublisherDaoImpl implements PublisherDao {
	
	@Override
	public Publisher getPublisher(String pid) {
		Publisher publisher = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_publisher where pid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pid);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				publisher = new Publisher(rs.getString("pid"), rs.getString("pname"));
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
		return publisher;
	}

	@Override
	public List<Publisher> list() {
		List<Publisher> list = new ArrayList<Publisher>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_publisher";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			Publisher publisher = null;
			while (rs.next()) {
				publisher = new Publisher(rs.getString("pid"), rs.getString("pname"));
				list.add(publisher);
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
		}return list;
	}

	@Override
	public String insert(Publisher publisher) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into t_publisher values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, publisher.getPid());
			ps.setString(2, publisher.getPname());
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
	public String remove(String pid) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from t_publisher where pid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pid);
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
		GoodsService gs = new GoodsServiceImpl();
		gs.updatePublisher(pid);
		return "1";
	}

	@Override
	public String update(Publisher publisher) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update t_publisher set pname = ? where pid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, publisher.getPname());
			ps.setString(2, publisher.getPid());
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
	public List<Publisher> getLikePublisherList(String inputs) {
		List<Publisher> list = new ArrayList<Publisher>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_publisher where pid like ? or pname like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+inputs+"%");
			ps.setString(2, "%"+inputs+"%");
			rs = ps.executeQuery();
			
			Publisher publisher = null;
			while (rs.next()) {
				publisher = new Publisher(rs.getString("pid"), rs.getString("pname"));
				list.add(publisher);
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
