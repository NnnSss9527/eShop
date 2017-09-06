package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.Publisher;

public interface PublisherDao {
	public Publisher getPublisher(String pid);
	public List<Publisher> list();
	public String insert(Publisher publisher);
	public String remove(String pid);
	public String update(Publisher publisher);
	public List<Publisher> getLikePublisherList(String inputs);
}
