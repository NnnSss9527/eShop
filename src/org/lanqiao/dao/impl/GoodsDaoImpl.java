package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.CategoryDao;
import org.lanqiao.dao.GoodsDao;
import org.lanqiao.dao.PublisherDao;
import org.lanqiao.entity.Category;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.entity.Publisher;
import org.lanqiao.util.DBUtil;

public class GoodsDaoImpl implements GoodsDao {
	PublisherDao pDao = new PublisherDaoImpl();
	CategoryDao cDao = new CategoryDaoImpl();

	@Override
	public PageInfo<Goods> pageInfo(String cid,int pageIndex,int pageSize) {
		PageInfo<Goods> pageInfo = new PageInfo<Goods>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select t2.* from (select t1.*,rownum rn from (select * from t_goods where cid = ?) t1 where rownum <= ?) t2 where rn >= ?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, cid);
			int startIndex = pageIndex * pageSize + 1;
			int endIndex = (pageIndex + 1) * pageSize;
			ps.setInt(2, endIndex);
			ps.setInt(3, startIndex);
			
			rs = ps.executeQuery();
			
			List<Goods> data = new ArrayList<Goods>();
			Goods goods = null;
			while (rs.next()) {
				goods = new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
				Publisher publisher = pDao.getPublisher(goods.getPid());
				goods.setPublisher(publisher);
				Category category = cDao.getCategory(goods.getCid());
				goods.setCategory(category);
				data.add(goods);
			}
			pageInfo.setPageIndex(pageIndex);
			pageInfo.setPageSize(pageSize);
			int totalNumber = total(cid);
			pageInfo.setTotalNumber(totalNumber);
			int totalPage = (totalNumber % pageSize == 0) ? (totalNumber / pageSize) : (totalNumber / pageSize + 1);
			pageInfo.setTotalPages(totalPage);
			pageInfo.setData(data);
			pageInfo.setFirstPage(pageIndex == 1);
			pageInfo.setLastPage(pageIndex == totalPage);
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
		return pageInfo;
	}

	@Override
	public int total(String cid) {
		int totalNumber = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) from t_goods where cid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				totalNumber = rs.getInt(1);
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
		return totalNumber;
	}

	@Override
	public Goods getGoods(String gid) {
		Goods goods = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_goods where gid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, gid);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				goods = new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
			}
			Publisher publisher = pDao.getPublisher(goods.getPid());
			goods.setPublisher(publisher);
			Category category = cDao.getCategory(goods.getCid());
			goods.setCategory(category);
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
		return goods;
	}

	@Override
	public List<Goods> getList(String search) {
		List<Goods> list = new ArrayList<Goods>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select t.gtitle,rownum from (select gtitle from t_goods where gtitle like ?) t where rownum < 6";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			
			rs = ps.executeQuery();
			Goods goods = null;
			while (rs.next()) {
				goods = new Goods(rs.getString("gtitle"));
				list.add(goods);
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
	public String insert(Goods goods) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into t_goods values(?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, goods.getGid());
			ps.setString(2, goods.getGtitle());
			ps.setString(3, goods.getGauthor());
			ps.setDouble(4, goods.getGsaleprice());
			ps.setDouble(5, goods.getGinprice());
			ps.setString(6, goods.getGdesc());
			ps.setString(7, goods.getGimg());
			ps.setInt(8, goods.getGclicks());
			ps.setString(9, goods.getCid());
			ps.setString(10, goods.getPid());
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
	public String remove(String gid) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from t_goods where gid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, gid);
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
	public PageInfo<Goods> getLikeGoodsList(String cid,String inputs,int pageIndex,int pageSize) {
		PageInfo<Goods> pageInfo = new PageInfo<Goods>();
		List<Goods> list = new ArrayList<Goods>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select t2.* from (select t1.*,rownum rn from (select * from (select * from t_goods where cid = ?) where gid like ? or gtitle like ? or gauthor like ? or gimg like ?) t1 where rownum <= ?) t2 where rn >= ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			for (int i = 2; i < 6; i++) {
				ps.setString(i, "%"+inputs+"%");
			}
			
			int startIndex = pageIndex * pageSize + 1;
			int endIndex = (pageIndex + 1) * pageSize;
			ps.setInt(6, endIndex);
			ps.setInt(7, startIndex);
			
			rs = ps.executeQuery();
			
			Goods goods = null;
			Publisher publisher = null;
			Category category = null;
			while (rs.next()) {
				goods = new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
				publisher = pDao.getPublisher(goods.getPid());
				goods.setPublisher(publisher);
				category = cDao.getCategory(goods.getCid());
				goods.setCategory(category);
				list.add(goods);
			}
			pageInfo.setPageIndex(pageIndex);
			pageInfo.setPageSize(pageSize);
			int totalNumber = totalLike(cid, inputs);
			pageInfo.setTotalNumber(totalNumber);
			int totalPage = (totalNumber % pageSize == 0) ? (totalNumber / pageSize) : (totalNumber / pageSize + 1);
			pageInfo.setTotalPages(totalPage);
			pageInfo.setData(list);
			pageInfo.setFirstPage(pageIndex == 1);
			pageInfo.setLastPage(pageIndex == totalPage);
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
		return pageInfo;
	}

	@Override
	public String update(Goods goods) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update t_goods set gtitle = ?,gauthor = ?,gsaleprice = ?,ginprice = ?,gdesc = ?,gimg = ?,gclicks = ?,cid = ?,pid = ? where gid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, goods.getGtitle());
			ps.setString(2, goods.getGauthor());
			ps.setDouble(3, goods.getGsaleprice());
			ps.setDouble(4, goods.getGinprice());
			ps.setString(5, goods.getGdesc());
			ps.setString(6, goods.getGimg());
			ps.setInt(7, goods.getGclicks());
			ps.setString(8, goods.getCid());
			ps.setString(9, goods.getPid());
			ps.setString(10, goods.getGid());
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
	public int totalLike(String cid, String inputs) {
		int totalNumber = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(0) from (select * from t_goods where cid = ?) where gid like ? or gtitle like ? or gauthor like ? or gimg like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			for (int i = 2; i < 6; i++) {
				ps.setString(i, "%"+inputs+"%");
			}
			rs = ps.executeQuery();
			
			if (rs.next()) {
				totalNumber = rs.getInt(1);
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
		return totalNumber;
	}

	@Override
	public void updateCategory(String cid) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update t_goods set cid = ? where cid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "8a99ab15-c4a7-42f6-8a25-6160725b9f1a");
			ps.setString(2, cid);
			ps.executeUpdate();
		} catch (SQLException e) {
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
	public void updatePublisher(String pid) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update t_goods set pid = ? where pid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "78932ab0-c11a-4a3b-ad1b-f830c75fc6e4");
			ps.setString(2, pid);
			ps.executeUpdate();
		} catch (SQLException e) {
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
	
	
}