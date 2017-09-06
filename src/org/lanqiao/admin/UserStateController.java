package org.lanqiao.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.UserState;
import org.lanqiao.service.UserStateService;
import org.lanqiao.service.impl.UserStateServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "userStateController", urlPatterns = { "/userstate.do" })
public class UserStateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		UserStateService uss = new UserStateServiceImpl();
		
		if (type != null && type.equals("select")) {
			List<UserState> list = uss.getListUserState();
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(list));
		}
		
		if (type != null && type.equals("selected")) {
			List<UserState> list = uss.userStateList();
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(list));
		}
		
		if (type != null && type.equals("update")) {
			String userid = request.getParameter("userid");
			String ustateid = request.getParameter("ustatename");
			String data = uss.updateUser(userid, ustateid);
			response.getWriter().write(data);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
