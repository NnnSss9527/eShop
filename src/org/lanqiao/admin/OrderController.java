package org.lanqiao.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Order;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.impl.OrderServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "orderController", urlPatterns = { "/order.do" })
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		OrderService os = new OrderServiceImpl();
		
		if (type != null && type.equals("selected")) {
			List<Order> list = os.listOrder();
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(list));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
