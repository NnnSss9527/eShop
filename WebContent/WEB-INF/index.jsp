<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

<!-- 广告   -->
<div id="divad">
<img src="${pageContext.request.contextPath }/images/ad/index_ad.jpg" />
</div>
<!-- 广告  end -->

<!-- 正文   -->
<div id="divcontent">
<table width="900px" border="0" cellspacing="0">
  <tr>
    <td width="497"><img src="${pageContext.request.contextPath }/images/billboard.gif" width="497" height="38" />      
    <table cellspacing="0" class="ctl">
    	<c:forEach items="${news }" var="item">
	        <tr>
	          <td>&middot;<a href="${pageContext.request.contextPath }/title.do?type=news&id=${item.tid }" style="color:#000000">${item.title }</a></td>
	        </tr>
        </c:forEach>
      </table></td>
    <td style="padding:5px 15px 10px 40px"><table width="100%" border="0" cellspacing="0">
      <tr>
        <td><img src="${pageContext.request.contextPath }/images/hottitle.gif" width="126" height="29" /></td>
        </tr>
    </table>
      <table width="100%" border="0" cellspacing="0">
        <tr>
          <td style="width:50; text-align:center"><a href="info.html"><img src="${pageContext.request.contextPath }/images/bookcover/travelbook.jpg" width="102" height="130" border="0" /></a><br />
            <a href="info.html">TravelBook<br/>
作者:Lonley Plant</a></td>
          <td style="width:50; text-align:center"><a href="info.html"><img src="${pageContext.request.contextPath }/images/bookcover/java2.jpg" width="102" height="130" border="0" /></a><br />
            <a href="info.html">Java2入门经典(JDK5) <br/>
作者:(美)霍顿</a></td>
        </tr>
      </table></td>
  </tr>
</table>
</div>
<!-- 正文 end   -->

<!-- 导入footer.jsp -->
<%@include file="footer.jsp" %>
</body>
</html>