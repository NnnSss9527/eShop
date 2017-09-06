<%@page import="org.lanqiao.entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="org.lanqiao.service.impl.CategoryServiceImpl"%>
<%@page import="org.lanqiao.service.CategoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/datalistsjQuery.js"></script>
<!-- Logo -->
<div id="divhead">
  <table cellspacing="0" class="headtable">
    <tr>
      <td><a href="${pageContext.request.contextPath }/index.do"><img src="${pageContext.request.contextPath }/images/logo.gif" width="95" height="30" border="0" /></a></td>
      <td style="text-align:right"><img src="${pageContext.request.contextPath }/images/cart.gif" width="26" height="23" style="margin-bottom:-4px"/>&nbsp;<a href="${pageContext.request.contextPath }/title.do?type=cart">购物车(${fn:length(sessionScope.cartGoodsList) })</a>　|　<a href="#">帮助中心</a>　|　<a href="${pageContext.request.contextPath }/title.do?type=loginSuccess">我的帐户</a>　|　<a href="${pageContext.request.contextPath }/title.do?type=registerPage">新用户注册</a></td>
    </tr>
  </table>
</div>
<!-- Logo end -->
    <%
	    CategoryService cs = new CategoryServiceImpl();
		List<Category> categories = cs.categoryList();
		request.setAttribute("categories", categories);
    %>
<!-- menu -->
<div id="divmenu">
	<c:forEach items="${categories }" var="item">
		<a href="${pageContext.request.contextPath }/title.do?type=category&id=${item.cid }&pageIndex=1">${item.cname}</a>　　
	</c:forEach>
	<a href="product_list.html" style="color:#FFFF00">全部商品目录</a>
</div>
<!-- menu end -->
<!-- search -->
<div id="divsearch"><table width="100%" border="0" cellspacing="0">
  <tr>
    <td style="text-align:right; padding-right:220px">Search
  <input type="text" name="textfield" id="search" class="inputtable" list="datalists"/>
  <datalist id="datalists"></datalist>
<a href="search.html"><img src="${pageContext.request.contextPath }/images/serchbutton.gif" border="0" style="margin-bottom:-4px"/></a></td>
  </tr>
</table>

</div>
<!-- search end -->