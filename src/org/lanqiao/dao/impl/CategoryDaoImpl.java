package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.CategoryDao;
import org.lanqiao.entity.Category;
import org.lanqiao.service.GoodsService;
import org.lanqiao.service.impl.GoodsServiceImpl;
import org.lanqiao.util.DBUtil;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> list() {
		List<Category> list = new ArrayList<Category>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_category";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			Category category = null;
			while (rs.next()) {
				category = new Category(rs.getString("cid"), rs.getString("cname"));
				list.add(category);
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
	public Category getCategory(String cid) {
		Category category = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_category where cid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				category = new Category(rs.getString("cid"), rs.getString("cname"));
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
		return category;
	}

	@Override
	public String insert(Category category) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into t_category values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCid());
			ps.setString(2, category.getCname());
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
	public String remove(String cid) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from t_category where cid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
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
		gs.updateCategory(cid);
		return "1";
	}

	@Override
	public String update(Category category) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update t_category set cname = ? where cid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCname());
			ps.setString(2, category.getCid());
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
	public List<Category> getLikeCategoryList(String inputs) {
		List<Category> list = new ArrayList<Category>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_category where cid like ? or cname like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+inputs+"%");
			ps.setString(2, "%"+inputs+"%");
			rs = ps.executeQuery();
			
			Category category = null;
			while (rs.next()) {
				category = new Category(rs.getString("cid"), rs.getString("cname"));
				list.add(category);
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