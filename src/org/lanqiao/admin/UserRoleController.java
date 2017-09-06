package org.lanqiao.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.UserRole;
import org.lanqiao.service.UserRoleService;
import org.lanqiao.service.impl.UserRoleServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "userRoleController", urlPatterns = { "/userrole.do" })
public class UserRoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		UserRoleService urs = new UserRoleServiceImpl();
		
		if (type != null && type.equals("select")) {
			List<UserRole> list = urs.getListUserRole();
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(list));
		}
		
		if (type != null && type.equals("selected")) {
			List<UserRole> list = urs.UserRoleList();
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(list));
		}
		
		if (type != null && type.equals("update")) {
			String userid = request.getParameter("userid");
			String uroleid = request.getParameter("urolename");
			String data = urs.updateUser(userid, uroleid);
			response.getWriter().write(data);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
