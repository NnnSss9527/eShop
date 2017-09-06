package org.lanqiao.admin;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.UserServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "userController", urlPatterns = { "/user.do" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		UserService us = new UserServiceImpl();
		if (type != null && type.equals("select")) {
			List<User> list = us.getUserLists();
			Gson gson = new Gson();
			String json = gson.toJson(list);
			response.getWriter().write(json);
		}
		
		if (type != null && type.equals("remove")) {
			String userid = request.getParameter("userid");
			String data = us.removeUserByUserId(userid);
			response.getWriter().write(data);
		}
		
		if (type != null && type.equals("insert")) {
			String userid = UUID.randomUUID().toString();
			String uemail = request.getParameter("uemail");
			String uloginid = request.getParameter("uloginid");
			String upassword = request.getParameter("upassword");
			String usex = request.getParameter("usex");
			String uaddress = request.getParameter("uaddress");
			String utel = request.getParameter("utel");
			String ustateid = "B5868B7A06E54DAEB19658343D3A2B28";
			String uroleid = "116F9526C319462780B9CA72F6BB9B41";
			
			User user = new User(userid, uemail, uloginid, upassword, usex, uaddress, utel, ustateid, uroleid);
			String data = us.insertUser(user);
			response.getWriter().write(data);
		}
		
		if (type != null && type.equals("update")) {
			String uemail = request.getParameter("uemail");
			String uloginid = request.getParameter("uloginid");
			String upassword = request.getParameter("upassword");
			String usex = request.getParameter("usex");
			String uaddress = request.getParameter("uaddress");
			String utel = request.getParameter("utel");
			
			User user = us.getUserByLoginId(uloginid);
			user.setUemail(uemail);
			user.setUpassword(upassword);
			user.setUsex(usex);
			user.setUaddress(uaddress);
			user.setUtel(utel);
			String data = us.updateUser(user);
			response.getWriter().write(data);
		}
		
		if (type != null && type.equals("search")) {
			String inputs = request.getParameter("inputs");
			List<User> list = us.getLikeUserList(inputs);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			response.getWriter().write(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
