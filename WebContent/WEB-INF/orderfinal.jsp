<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta content="all" name="robots"/>
<meta name="author" content="Fisher" />
<meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
<meta name="description" content="reefdesign" />
<meta name="keywords" content="reefdesign" />
<title>电子书城</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath }/images/favicon.ico" />
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css" media="all" />
</head>

<body class="main">
<!-- 导入header.jsp -->
<%@include file="header.jsp" %>

<div id="divpagecontent">
  <table width="100%" border="0" cellspacing="0">
    <tr>
      <td width="25%"><table width="100%" border="0" cellspacing="0" style="margin-top:30px">
        <tr>
          <td class="listtitle">配送方式、时间及费用</td>
        </tr>
        <tr>
          <td class="listtd"><img src="${pageContext.request.contextPath }/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="news.html">普通快递送货上门</a></td>
        </tr>
		<tr>
          <td class="listtd"><img src="${pageContext.request.contextPath }/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">特快专递送货上门(EMS)</a></td>
        </tr>
		<tr>
          <td class="listtd"><img src="${pageContext.request.contextPath }/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">申通快递送货上门</a></td>
        </tr>
		<tr>
          <td class="listtd"><img src="${pageContext.request.contextPath }/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">普通邮寄</a></td>
        </tr>
      </table></td>
      
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="${pageContext.request.contextPath }/index.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/title.do?type=cart">&nbsp;购物车</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;确认订单</div>
		
        <table cellspacing="0" class="infocontent">
        <tr>
          <td><table width="100%" border="0" cellspacing="0">
              <tr>
                <td><img src="${pageContext.request.contextPath }/images/buy3.gif" width="635" height="38" />
                  <p>您好：${sessionScope.user.uloginid }<c:if test='${sessionScope.user.usex == "男" }'>先生</c:if><c:if test='${sessionScope.user.usex == "女" }'>女士</c:if>！您的购买流程还有最后一步啦</p></td>
              </tr>
              <tr>
                <td><table cellspacing="1" class="carttable">
                  <tr>
                    <td width="10%">序号</td>
                    <td width="40%">商品名称</td>
                    <td width="10%">市场价</td>
                    <td width="10%">优惠价</td>
                    <td width="10%">数量</td>
                    <td width="10%">小计</td>
                  </tr>
                </table>
				  <c:if test="${sessionScope.cartGoodsList != null }">
                	<c:set var="totalprice" value="0"></c:set>
                	<c:forEach items="${cartGoodsList }" var="cartGoods">
	                  <table width="100%" border="0" cellspacing="0">
	                    <tr>
	                      <td width="10%">${cartGoods.gid }</td>
	                      <td width="40%">${cartGoods.gtitle }</td>
	                      <td width="10%">${cartGoods.gsaleprice }</td>
	                      <td width="10%">${cartGoods.ginprice }</td>
	                      <td width="10%">${cartGoods.count }</td>
	                      <td width="10%">${cartGoods.ginprice*cartGoods.count }</td>
	                      <c:set var="totalprice" value="${totalprice+cartGoods.ginprice*cartGoods.count }"></c:set>
	                    </tr>
	                  </table>
                  	</c:forEach>
                 </c:if>
				  
				   <table cellspacing="1" class="carttable">
                     <tr>
                       <td style="text-align:right; padding-right:40px;"><font style="color:#FF0000">合计：&nbsp;&nbsp;${totalprice } 元</font></td>
                      </tr>
                   </table>
				   <p>收货地址：上海市XXX路XXXX号XXXX室<br/>
				   收货人：Tony<br/>
				   联系方式：13600000000</p>
				   <hr/> 
				   <p style="text-align:center"><a href="${pageContext.request.contextPath }/title.do?type=ordersuccess"><img src="${pageContext.request.contextPath }/images/finalbutton.gif" width="204" height="51" border="0" /></a></p></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
    </tr>
  </table>
</div>

<!-- 导入footer.jsp -->
<%@include file="footer.jsp" %>
</body>
</html>
