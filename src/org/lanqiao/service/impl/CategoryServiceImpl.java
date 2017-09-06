package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.CategoryDao;
import org.lanqiao.dao.impl.CategoryDaoImpl;
import org.lanqiao.entity.Category;
import org.lanqiao.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	CategoryDao dao = new CategoryDaoImpl();
	@Override
	public List<Category> categoryList() {
		return dao.list();
	}
	@Override
	public Category getCategoryById(String cid) {
		return dao.getCategory(cid);
	}
	@Override
	public String insertCategory(Category category) {
		return dao.insert(category);
	}
	@Override
	public String removeCategoryByCid(String cid) {
		return dao.remove(cid);
	}
	@Override
	public String updateCategory(Category category) {
		return dao.update(category);
	}
	@Override
	public List<Category> getLikeCategoryList(String inputs) {
		return dao.getLikeCategoryList(inputs);
	}

}
