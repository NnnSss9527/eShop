package org.lanqiao.admin;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.service.GoodsService;
import org.lanqiao.service.impl.GoodsServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "goodsController", urlPatterns = { "/goods.do" })
public class GoodsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		GoodsService gs = new GoodsServiceImpl();
		
		if (type != null && type.equals("select")) {
			String cid = request.getParameter("cid");
			int pageIndex = Integer.parseInt(request.getParameter("page")) - 1;
			int pageSize = Integer.parseInt(request.getParameter("rows"));
			PageInfo<Goods> pageInfo = gs.PageInfoList(cid, pageIndex, pageSize);
			int total = pageInfo.getTotalNumber();
			List<Goods> rows = pageInfo.getData();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", total);
			map.put("rows", rows);
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(map));
		}
		
		if (type != null && type.equals("insert")) {
			String gtitle = request.getParameter("gtitle");
			String gauthor = request.getParameter("gauthor");
			double gsaleprice = Double.parseDouble(request.getParameter("gsaleprice"));
			double ginprice = Double.parseDouble(request.getParameter("ginprice"));
			String gdesc = "1551484fd54f1d64t51464t416yn5y4y5ndvs54c2";
			String gimg = request.getParameter("gimg");
			int gclicks = Integer.parseInt(request.getParameter("gclicks"));
			String cid = request.getParameter("cname");
			String pid = request.getParameter("pname");
			String gid = cid + "-" + new Date().getTime();
			Goods goods = new Goods(gid, gtitle, gauthor, gsaleprice, ginprice, gdesc, gimg, gclicks, cid, pid);
			String data = gs.insertGoods(goods);
			response.getWriter().write(data);
		}
		
		if (type != null && type.equals("find")) {
			String gid = request.getParameter("gid");
			Goods goods = gs.getGoodsById(gid);
			if (goods != null) {
				response.getWriter().write("1");
			} else {
				response.getWriter().write("0");
			}
		}
		
		if (type != null && type.equals("remove")) {
			String gid = request.getParameter("gid");
			String data = gs.removeGoodsByGid(gid);
			response.getWriter().write(data);
		}
		
		if (type != null && type.equals("search")) {
			String cid = request.getParameter("cid");
			String inputs = request.getParameter("inputs");
			int pageIndex = Integer.parseInt(request.getParameter("page")) - 1;
			int pageSize = Integer.parseInt(request.getParameter("rows"));
			PageInfo<Goods> pageInfo = gs.getLikeGoodsList(cid, inputs, pageIndex, pageSize);
			int total = pageInfo.getTotalNumber();
			List<Goods> rows = pageInfo.getData();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", total);
			map.put("rows", rows);
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(map));
		}
		
		if (type != null && type.equals("update")) {
			String gid = request.getParameter("gid");
			Goods goods = gs.getGoodsById(gid);
			String gtitle = request.getParameter("gtitle");
			System.out.println(gtitle);
			String gauthor = request.getParameter("gauthor");
			double gsaleprice = Double.parseDouble(request.getParameter("gsaleprice"));
			double ginprice = Double.parseDouble(request.getParameter("ginprice"));
			String gimg = request.getParameter("gimg");
			int gclicks = Integer.parseInt(request.getParameter("gclicks"));
			String cid = request.getParameter("cname");
			String pid = request.getParameter("pname");
			goods.setGtitle(gtitle);
			goods.setGauthor(gauthor);
			goods.setGsaleprice(gsaleprice);
			goods.setGinprice(ginprice);
			goods.setGimg(gimg);
			goods.setGclicks(gclicks);
			goods.setCid(cid);
			goods.setPid(pid);
//			String data = gs.upadteGoods(goods);
//			response.getWriter().write(data);
		}
		
		if (type != null && type.equals("uploadImg")) {
			System.out.println("33333333333333");
			Part part = request.getPart("gimg");
			System.out.println(part + "22222");
			System.out.println("1111111111111");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
