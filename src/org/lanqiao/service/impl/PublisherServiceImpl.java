package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.PublisherDao;
import org.lanqiao.dao.impl.PublisherDaoImpl;
import org.lanqiao.entity.Publisher;
import org.lanqiao.service.PublisherService;

public class PublisherServiceImpl implements PublisherService {
	PublisherDao dao = new PublisherDaoImpl();
	@Override
	public Publisher getPublisherById(String pid) {
		return dao.getPublisher(pid);
	}
	@Override
	public List<Publisher> publisherList() {
		return dao.list();
	}
	@Override
	public String insertPublisher(Publisher publisher) {
		return dao.insert(publisher);
	}
	@Override
	public String removePublisherByPid(String pid) {
		return dao.remove(pid);
	}
	@Override
	public String updatePublisher(Publisher publisher) {
		return dao.update(publisher);
	}
	@Override
	public List<Publisher> getLikePublisherList(String inputs) {
		return dao.getLikePublisherList(inputs);
	}

}
