package org.lanqiao.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Publisher;
import org.lanqiao.service.PublisherService;
import org.lanqiao.service.impl.PublisherServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "publisherContrller", urlPatterns = { "/publisher.do" })
public class PublisherContrller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		PublisherService ps = new PublisherServiceImpl();
		
		if (type != null && type.equals("select")) {
			List<Publisher> list = ps.publisherList();
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(list));
		}
		
		if (type!= null && type.equals("insert")) {
			String pid = request.getParameter("pid");
			String pname = request.getParameter("pname");
			Publisher publisher = new Publisher(pid, pname);
			String data = ps.insertPublisher(publisher);
			response.getWriter().write(data);
		}
		
		if (type!= null && type.equals("remove")) {
			String pid = request.getParameter("pid");
			String data = ps.removePublisherByPid(pid);
			response.getWriter().write(data);
		}
		
		if (type!= null && type.equals("update")) {
			String pid = request.getParameter("pid");
			String pname = request.getParameter("pname");
			Publisher publisher = new Publisher(pid, pname);
			String data = ps.updatePublisher(publisher);
			response.getWriter().write(data);
		}
		
		if (type!= null && type.equals("search")) {
			String inputs = request.getParameter("inputs");
			List<Publisher> list = ps.getLikePublisherList(inputs);
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(list));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
