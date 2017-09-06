package org.lanqiao.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Category;
import org.lanqiao.service.CategoryService;
import org.lanqiao.service.impl.CategoryServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "categoryController", urlPatterns = { "/category.do" })
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		CategoryService cs = new CategoryServiceImpl();
		
		if (type!= null && type.equals("select")) {
			List<Category> list = cs.categoryList();
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(list));
		}
		
		if (type!= null && type.equals("insert")) {
			String cid = request.getParameter("cid");
			String cname = request.getParameter("cname");
			Category category = new Category(cid, cname);
			String data = cs.insertCategory(category);
			response.getWriter().write(data);
		}
		
		if (type!= null && type.equals("remove")) {
			String cid = request.getParameter("cid");
			String data = cs.removeCategoryByCid(cid);
			response.getWriter().write(data);
		}
		
		if (type!= null && type.equals("update")) {
			String cid = request.getParameter("cid");
			String cname = request.getParameter("cname");
			Category category = new Category(cid, cname);
			String data = cs.updateCategory(category);
			response.getWriter().write(data);
		}
		
		if (type!= null && type.equals("search")) {
			String inputs = request.getParameter("inputs");
			List<Category> list = cs.getLikeCategoryList(inputs);
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(list));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}