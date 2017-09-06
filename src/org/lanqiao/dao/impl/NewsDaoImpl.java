package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.NewsDao;
import org.lanqiao.entity.News;
import org.lanqiao.util.DBUtil;

public class NewsDaoImpl implements NewsDao {

	@Override
	public List<News> list() {
		List<News> list = new ArrayList<News>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_news";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			News news = null;
			while (rs.next()) {
				news = new News(rs.getString("tid"), rs.getString("title"), rs.getString("tcontent"), rs.getDate("tpubdate"));
				list.add(news);
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
	public News getNews(String id) {
		News news = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from t_news where tid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				news = new News(rs.getString("tid"), rs.getString("title"), rs.getString("tcontent"), rs.getDate("tpubdate"));
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
		return news;
	}
	
}
