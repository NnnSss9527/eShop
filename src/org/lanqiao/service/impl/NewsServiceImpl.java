package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.NewsDao;
import org.lanqiao.dao.impl.NewsDaoImpl;
import org.lanqiao.entity.News;
import org.lanqiao.service.NewsService;

public class NewsServiceImpl implements NewsService {
	NewsDao dao = null;
	public NewsServiceImpl() {
		dao = new NewsDaoImpl();
	}

	@Override
	public List<News> newsList() {
		return dao.list();
	}

	@Override
	public News getNewsById(String tid) {
		return dao.getNews(tid);
	}
}
