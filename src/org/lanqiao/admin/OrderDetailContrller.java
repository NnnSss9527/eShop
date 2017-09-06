package org.lanqiao.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.OrderDetail;
import org.lanqiao.service.OrderDetailService;
import org.lanqiao.service.impl.OrderDetailServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "orderDetailContrller", urlPatterns = { "/orderdetail.do" })
public class OrderDetailContrller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		OrderDetailService ods = new OrderDetailServiceImpl();
		
		if (type != null && type.equals("selected")) {
			String orderid = request.getParameter("orderid");
			List<OrderDetail> list = ods.lisTOrderDetail(orderid);
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(list));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
