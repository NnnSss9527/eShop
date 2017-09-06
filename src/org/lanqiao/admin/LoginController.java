package org.lanqiao.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.UserServiceImpl;

@WebServlet(name = "loginController", urlPatterns = { "/login.do" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String uloginid = request.getParameter("uloginid");
			String upassword = request.getParameter("upassword");
			UserService us = new UserServiceImpl();
			User user = us.getUserById(uloginid, upassword);
			if (user != null) {
				if (user.getUroleid().equals("377D0AE90A804D289F301FB085A6E9AA")) {
					response.getWriter().write("1");
				}else if (user.getUstateid().equals("36D0F394FC6A45829385E0BE11208263")) {
					response.getWriter().write("2");
				} else {
					response.getWriter().write("-1");
				}
			} else {
				response.getWriter().write("0");
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
