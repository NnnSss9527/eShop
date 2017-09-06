package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.Category;

public interface CategoryService {
	public List<Category> categoryList();
	public Category getCategoryById(String cid);
	public String insertCategory(Category category);
	public String removeCategoryByCid(String cid);
	public String updateCategory(Category category);
	public List<Category> getLikeCategoryList(String inputs);
}
