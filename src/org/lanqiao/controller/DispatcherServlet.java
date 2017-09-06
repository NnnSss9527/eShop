package org.lanqiao.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.entity.CartCookie;
import org.lanqiao.entity.CartGoods;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.News;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;
import org.lanqiao.service.GoodsService;
import org.lanqiao.service.NewsService;
import org.lanqiao.service.OrderDetailService;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.PasswordAnswerService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.GoodsServiceImpl;
import org.lanqiao.service.impl.NewsServiceImpl;
import org.lanqiao.service.impl.OrderDetailServiceImpl;
import org.lanqiao.service.impl.OrderServiceImpl;
import org.lanqiao.service.impl.PasswordAnswerServiceImpl;
import org.lanqiao.service.impl.UserServiceImpl;
import org.lanqiao.util.MailUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class TitleServlet
 */
@WebServlet(name = "title", urlPatterns = { "/title.do" })
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		//用户点击了新闻信息
		if (type != null && type.equals("news")) {
			NewsService ns = new NewsServiceImpl();
			News news = ns.getNewsById(id);
			request.setAttribute("news", news);
			request.getRequestDispatcher("/WEB-INF/title.jsp").forward(request, response);
		}
		//用户选择了商品类别
		if (type != null && type.equals("category")) {
			String pageIndex = request.getParameter("pageIndex");
			GoodsService gs = new GoodsServiceImpl();
			PageInfo<Goods> pageInfo = gs.PageInfoList(id, Integer.parseInt(pageIndex) - 1, 5);
			request.setAttribute("pageInfo", pageInfo);
			request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response);
		}
		//用户点击了某个商品
		if (type != null && type.equals("goods")) {
			GoodsService gs = new GoodsServiceImpl();
			Goods goods = gs.getGoodsById(id);
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("/WEB-INF/info.jsp").forward(request, response);
		}
		//用户点击了新用户注册
		if (type != null && type.equals("registerPage")) {
			request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		}
		//用户提交了注册信息
		if (type != null && type.equals("register")) {
			String checkCodes = request.getSession().getAttribute("codes").toString();
			String inputCodes = request.getParameter("inputCodes");
			if (!checkCodes.equalsIgnoreCase(inputCodes)) {
				request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
				return;
			}
			//获取用户填写个人信息并存到数据库
			String userid = UUID.randomUUID().toString();
			String uemail = request.getParameter("uemail");
			String uloginid = request.getParameter("uname");
			String upassword = request.getParameter("upassword");
			String usex = request.getParameter("usex");
			String uaddress = request.getParameter("uaddress");
			String utel = request.getParameter("utel");
			String ustateid = "B5868B7A06E54DAEB19658343D3A2B28";
			String uroleid = "377D0AE90A804D289F301FB085A6E9AA";
			String uemailstataeid = "22699ca4-a7a2-418e-8d13-eea2ed70d216";
			User user = new User(userid, uemail, uloginid, upassword, usex, uaddress, utel, ustateid, uroleid, uemailstataeid);
			UserService us = new UserServiceImpl();
			us.insertUser(user);
			//获取用户填写验证问题信息并存到数据库
			String answerid = UUID.randomUUID().toString();
			String question = request.getParameter("question");
			String answer = request.getParameter("answer");
			String email = request.getParameter("email");
			PasswordAnswer passwordAnswer = new PasswordAnswer(answerid, question, answer, email, userid);
			PasswordAnswerService pas = new PasswordAnswerServiceImpl();
			pas.insertPasswordAnswer(passwordAnswer);
			//发送验证邮件
			String url = "localhost:8080/eShop/title.do?type=checkEmail&userid=" + userid;
			MailUtil.sentEmail(uemail, uloginid, url, "registerEmail");
			
			request.getRequestDispatcher("/WEB-INF/registersuccess.jsp").forward(request, response);
		}
		//图片验证码的生成
		if (type != null && type.equals("checkCode")) {
			//1、生成验证字符串
			String chars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
			Random random = new Random();
			String codes = "";
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < 4; i++) {
				stringBuilder.append(chars.charAt(random.nextInt(61)));
			}
			codes = stringBuilder.toString();
			
			//2、生成内存中的图片
			BufferedImage bufferedImage = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
			
			Graphics graphics = bufferedImage.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(1, 1, 78, 28);
			
			graphics.setColor(Color.BLACK);
			graphics.drawRect(0, 0, 80, 30);
			
			graphics.setColor(Color.BLUE);
			graphics.setFont(new Font("宋体", Font.BOLD, 20));
			graphics.drawString(codes, 20, 20);
			
			request.getSession().setAttribute("codes", codes);
			
			graphics.setColor(Color.RED);
			for (int i = 0; i < 5; i++) {
				graphics.drawLine(random.nextInt(80), random.nextInt(30), random.nextInt(80), random.nextInt(30));
			}
			
			response.setContentType("image");
			ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
		}
		//用户点击了我的账户
		if (type != null && type.equals("loginSuccess")) {
			Cookie[] cookies = request.getCookies();
			Cookie cookie = null;
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("uname")) {
						cookie = c;
						break;
					}
				}
			}
			if (cookie != null) {
				UserService us = new UserServiceImpl();
				User user = us.getUserByLoginId(cookie.getValue());
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/my.jsp").forward(request, response);
				return;
			}
			HttpSession session = request.getSession();
			if (session.getAttribute("user") != null) {
				session.setAttribute("user", (User)session.getAttribute("user"));
				session.setMaxInactiveInterval(60*30);
				request.getRequestDispatcher("/WEB-INF/my.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		//用户提交了登录信息
		if (type != null && type.equals("login")) {
			String uname = request.getParameter("uname");
			String upassword = request.getParameter("upassword");
			
			UserService us = new UserServiceImpl();
			User user = us.getUserById(uname, upassword);
			
			if (user == null) {
				request.setAttribute("loginInfo", "用户名或密码错误！");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
				return;
			}
			if (user.getUemailstateid().equals("22699ca4-a7a2-418e-8d13-eea2ed70d216")) {
				request.setAttribute("loginInfo", "邮箱未激活！请先激活邮箱！");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
				return;
			}
			if (user.getUstateid().equals("36D0F394FC6A45829385E0BE11208263")) {
				request.setAttribute("loginInfo", "账号无效，请联系管理员解锁！");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
				return;
			}
			String checkbox = request.getParameter("checkbox");
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(60*30);
			if (checkbox != null) {
				Cookie cookie = new Cookie("uname", uname);
				cookie.setMaxAge(60*60*24*7);
				response.addCookie(cookie);
			}
			request.getRequestDispatcher("/WEB-INF/my.jsp").forward(request, response);
		}
		//用户点击了修改个人信息
		if (type != null && type.equals("modifyuserinfo")) {
			request.getRequestDispatcher("/WEB-INF/modifyuserinfo.jsp").forward(request, response);
		}
		//用户提交了修改后的个人信息
		if (type != null && type.equals("modifyuserinfoSueecss")) {
			String checkCodes = request.getSession().getAttribute("codes").toString();
			String inputCodes = request.getParameter("inputCodes");
			if (!checkCodes.equalsIgnoreCase(inputCodes)) {
				request.getRequestDispatcher("/WEB-INF/modifyuserinfo.jsp").forward(request, response);
				return;
			}
			//获取用户填写修改信息并存到数据库
			User user = (User) request.getSession().getAttribute("user");
			String upassword = request.getParameter("upassword");
			String usex = request.getParameter("usex");
			String uaddress = request.getParameter("uaddress");
			String utel = request.getParameter("utel");
			user.setUpassword(upassword);
			user.setUsex(usex);
			user.setUaddress(uaddress);
			user.setUtel(utel);
			UserService us = new UserServiceImpl();
			us.updateUser(user);
			//获取用户填写验证问题信息并存到数据库
			String question = request.getParameter("question");
			String answer = request.getParameter("answer");
			String email = request.getParameter("email");
			
			PasswordAnswerService pas = new PasswordAnswerServiceImpl();
			PasswordAnswer passwordAnswer = pas.getPasswordAnswerByUserId(user.getUserid());
			passwordAnswer.setQuestion(question);
			passwordAnswer.setAnswer(answer);
			passwordAnswer.setEmail(email);
			pas.updatePasswordAnswer(passwordAnswer);
			
			request.getRequestDispatcher("/WEB-INF/success.jsp").forward(request, response);
		}
		//用户点击购物车
		if (type != null && type.equals("cart")) {
			Cookie[] cookies = request.getCookies();
			Cookie cookie = null;
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("json")) {
						cookie = c;
						break;
					}
				}
			}
			List<CartCookie> cartCookieList = null;
			if (cookie != null) {
				Gson gson = new Gson();
				String json = cookie.getValue();
				TypeToken<List<CartCookie>> listType = new TypeToken<List<CartCookie>>(){};
				cartCookieList = gson.fromJson(json, listType.getType());
			}
			if (cartCookieList != null) {
				setSession(request, cartCookieList);
			}
			request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
		}
		//用户添加商品到购物车
		if (type != null && type.equals("buy")) {
			CartCookie cartCookie = new CartCookie(id, 1);
			Cookie[] cookies = request.getCookies();
			Cookie cookie = null;
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("json")) {
						cookie = c;
						break;
					}
				}
			}
			List<CartCookie> cartCookieList = new ArrayList<CartCookie>();
			if (cookie == null) {
				cartCookieList.add(cartCookie);
			}
			if (cookie != null) {
				Gson gson = new Gson();
				String json = cookie.getValue();
				TypeToken<List<CartCookie>> listType = new TypeToken<List<CartCookie>>(){};
				cartCookieList = gson.fromJson(json, listType.getType());
				CartCookie cartCookies = null;
				for (CartCookie c : cartCookieList) {
					if (c.getGid().equals(id)) {
						cartCookies = c;
						break;
					}
				}
				if (cartCookies == null) {
					cartCookieList.add(cartCookie);
				}
				if (cartCookies != null) {
					cartCookies.setCount(cartCookies.getCount() + 1);
				}
			}
			Gson gson = new Gson();
			String json = gson.toJson(cartCookieList);
			Cookie cookieJson = new Cookie("json", json);
			cookieJson.setMaxAge(60*60*24*365);
			response.addCookie(cookieJson);
			setSession(request, cartCookieList);
			request.getRequestDispatcher("/WEB-INF/addsuccess.jsp").forward(request, response);
		}
		//用户点击了购物车的结账
		if (type != null && type.equals("order")) {
			User user = (User) request.getSession().getAttribute("user");
			if (user == null) {
				request.getRequestDispatcher("/title.do?type=loginSuccess").forward(request, response);
				return;
			}
			request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
		}
		//用户点击了提交订单
		if (type != null && type.equals("orderfinal")) {
			request.getRequestDispatcher("/WEB-INF/orderfinal.jsp").forward(request, response);
		}
		//用户最终确认了订单
		if (type != null && type.equals("ordersuccess")) {
			Cookie[] cookies = request.getCookies();
			Cookie cookie = null;
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("json")) {
						cookie = c;
						break;
					}
				}
			}
			List<CartCookie> cartCookieList = null;
			if (cookie != null) {
				Gson gson = new Gson();
				String json = cookie.getValue();
				TypeToken<List<CartCookie>> listType = new TypeToken<List<CartCookie>>(){};
				cartCookieList = gson.fromJson(json, listType.getType());
			}
			if (cartCookieList != null) {
				String orderid = UUID.randomUUID().toString();
				String userid = ((User) request.getSession().getAttribute("user")).getUserid();
				Date orderdate = new Date();
				
				double totalprice = 0;
				GoodsService gs = new GoodsServiceImpl();
				for (CartCookie c : cartCookieList) {
					Goods goods = gs.getGoodsById(c.getGid());
					totalprice += goods.getGinprice() * c.getCount();
				}
				OrderService os = new OrderServiceImpl();
				Order order = new Order(orderid, userid, new BigDecimal(totalprice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(), orderdate);
				os.insertOrder(order);
				OrderDetailService ods = new OrderDetailServiceImpl();
				OrderDetail orderDetail = null;
				for (CartCookie c : cartCookieList) {
					Goods goods = gs.getGoodsById(c.getGid());
					orderDetail = new OrderDetail(UUID.randomUUID().toString(), goods.getGtitle(), goods.getGinprice(), c.getCount(), orderid, goods.getGid());
					ods.insertOrderDetail(orderDetail);
				}
			}
			if (cookies != null) {
				Cookie cookie2 = null;
				for (Cookie c : cookies) {
					if (c.getName().equals("json")) {
						cookie2 = c;
						break;
					}
				}
				cookie2.setMaxAge(0);
				response.addCookie(cookie2);
			}
			setSession(request, null);
			request.getRequestDispatcher("/WEB-INF/ordersuccess.jsp").forward(request, response);
		}
		//删除购物车单条记录
		if (type != null && type.equals("cartremove")) {
			Cookie[] cookies = request.getCookies();
			Cookie cookie = null;
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("json")) {
						cookie = c;
						break;
					}
				}
			}
			List<CartCookie> cartCookieList = null;
			if (cookie != null) {
				Gson gson = new Gson();
				String json = cookie.getValue();
				TypeToken<List<CartCookie>> listType = new TypeToken<List<CartCookie>>(){};
				cartCookieList = gson.fromJson(json, listType.getType());
			}
			if (cartCookieList != null) {
				CartCookie cartCookie = null;
				for (CartCookie c : cartCookieList) {
					if (c.getGid().equals(id)) {
						cartCookie = c;
					}
				}
				cartCookieList.remove(cartCookie);
			}
			Gson gson = new Gson();
			String json = gson.toJson(cartCookieList);
			Cookie cookieJson = new Cookie("json", json);
			cookieJson.setMaxAge(60*60*24*365);
			response.addCookie(cookieJson);
			setSession(request, cartCookieList);
			request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
		}
		//用户名验证
		if (type != null && type.equals("unamecheck")) {
			String uname = request.getParameter("uname");
			uname = URLDecoder.decode(uname, "UTF-8");
			UserService us = new UserServiceImpl();
			User user = us.getUserByLoginId(uname);
			if (user == null) {
				response.getWriter().write("0");
			}
			if (user != null) {
				response.getWriter().write("1");
			}
		}
		//邮箱验证
		if (type != null && type.equals("uemailcheck")) {
			String uemail = request.getParameter("uemail");
			uemail = URLDecoder.decode(uemail, "UTF-8");
			UserService us = new UserServiceImpl();
			User user = us.getUserByUEmail(uemail);
			if (user == null) {
				response.getWriter().write("0");
			}
			if (user != null) {
				response.getWriter().write("1");
			}
		}
		//datalist搜索提示窗
		if (type != null && type.equals("searchdatalist")) {
			String search = request.getParameter("search");
			search = URLDecoder.decode(search, "UTF-8");
			GoodsService gs = new GoodsServiceImpl();
			List<Goods> list = gs.getListByGTitle(search);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			response.setContentType("application/json");
			response.getWriter().write(json);
		}
		//Ajax分页
		if (type != null && type.equals("pagination")) {
			String pageIndex = request.getParameter("pageIndex");
			if (pageIndex == null) {
				pageIndex = "0";
			}
			String pageSize = request.getParameter("pageSize");
			if (pageSize == null) {
				pageSize = "5";
			}
			GoodsService gs = new GoodsServiceImpl();
			PageInfo<Goods> pageInfo = gs.PageInfoList("3", Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
			Gson gson = new Gson();
			String json = gson.toJson(pageInfo);
			response.getWriter().write(json);
		}
		
		//邮箱激活
		if (type != null && type.equals("checkEmail")) {
			String userid = request.getParameter("userid");
			UserService us = new UserServiceImpl();
			User user = us.getUserByUserid(userid);
			if (user != null ) {
				if (user.getUemailstateid().equals("22699ca4-a7a2-418e-8d13-eea2ed70d216")) {
					String data = us.updateUemail(user);
					if (data.equals("1")) {
						request.getRequestDispatcher("/WEB-INF/emailsuccess.jsp").forward(request, response);
						return;
					}
				} else {
					request.setAttribute("uemailInfo", "邮箱已激活！无需重复操作！");
					return;
				}
			} 
			request.setAttribute("uemailInfo", "用户不存在，请重新点击链接或复制完整链接到地址栏！");
			request.getRequestDispatcher("/WEB-INF/registersuccess.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void setSession(HttpServletRequest request,List<CartCookie> cartCookieList) {
		List<CartGoods> cartGoodsList  = new ArrayList<CartGoods>();
		CartGoods cartGoods = null;
		GoodsService gs = new GoodsServiceImpl();
		if (cartCookieList != null) {
			for (CartCookie c : cartCookieList) {
				Goods goods = gs.getGoodsById(c.getGid());
				cartGoods = new CartGoods(c.getGid(), goods.getGtitle(), goods.getGsaleprice(), goods.getGinprice(), c.getCount());
				cartGoodsList.add(cartGoods);
			}
		}
		if (cartCookieList == null) {
			cartGoodsList = null;
		}
		HttpSession session = request.getSession();
		session.setAttribute("cartGoodsList", cartGoodsList);
		session.setMaxInactiveInterval(60*60*2);
	}
}