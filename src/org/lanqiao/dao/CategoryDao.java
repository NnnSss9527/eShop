package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.Category;

public interface CategoryDao {
	public List<Category> list();
	public Category getCategory(String cid);
	public String insert(Category category);
	public String remove(String cid);
	public String update(Category category);
	public List<Category> getLikeCategoryList(String inputs);
}
