package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.Publisher;

public interface PublisherService {
	public Publisher getPublisherById(String pid);
	public List<Publisher> publisherList();
	public String insertPublisher(Publisher publisher);
	public String removePublisherByPid(String Pid);
	public String updatePublisher(Publisher publisher);
	public List<Publisher> getLikePublisherList(String inputs);
}
