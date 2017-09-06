<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta content="all" name="robots" />
    <meta name="author" content="Fisher" />
    <meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
    <meta name="description" content="reefdesign" />
    <meta name="keywords" content="reefdesign" />
    <title>电子书城</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
    <link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css" media="all" />
</head>
<body class="main">
    <!-- 导入header.jsp -->
	<%@include file="header.jsp" %>
	
    <div id="divpagecontent">
        <table width="100%" border="0" cellspacing="0">
            <tr>
                <td>
                    <div style="text-align: right; margin: 5px 10px 5px 0px">
                        <a href="${pageContext.request.contextPath }/index.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${pageInfo.data[0].category.cname }
                    </div>
                    <table cellspacing="0" class="infocontent">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellspacing="0">
                                    <tr>
                                        <td style="padding: 10px">
                                            以下 <strong>${pageInfo.totalNumber }</strong> 条  当前页显示<strong>${fn:length(pageInfo.data) }</strong>条<hr />
                                            
                                        <c:forEach items="${pageInfo.data }" var="goods">
                                            <table border="0" cellspacing="0" class="searchtable">
                                                <tr>
                                                    <td width="20%" rowspan="2">
                                                        <div class="divbookpic">
                                                            <p>
                                                                <a href="${pageContext.request.contextPath }/title.do?type=goods&id=${goods.gid }">
                                                                    <img src="${pageContext.request.contextPath }/images/bookcover/${goods.gimg }" width="115" height="129" border="0" /></a></p>
                                                        </div>
                                                    </td>
                                                    <td colspan="2">
                                                        <font class="bookname">${goods.gtitle }</font><br />
                                                        作者：${goods.gauthor } 著<br />
                                                        ${goods.gdesc }
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        售价：<font color="#FF0000">￥${goods.ginprice }</font>&nbsp;&nbsp;&nbsp;&nbsp;原价：<s>￥${goods.gsaleprice }</s>
                                                    </td>
                                                    <td style="text-align: right">
                                                        <a href="${pageContext.request.contextPath }/title.do?type=buy&id=${goods.gid }">
                                                            <img src="${pageContext.request.contextPath }/images/buy.gif" width="91" height="27" border="0" style="margin-bottom: -8px" /></a>
                                                    </td>
                                                </tr>
                                            </table>
                                        </c:forEach>
                                        
                                            <div class="pagination">
                                                <ul>
                                                	<c:if test="${pageInfo.isFirstPage }">
                                                		<li class="disablepage">首页 </li>
                                                    	<li class="disablepage">&lt;&lt; 上一页 </li>
                                                    </c:if>
                                                    <c:if test="${!pageInfo.isFirstPage }">
                                                    	<li><a href="${pageContext.request.contextPath }/title.do?type=category&id=${pageInfo.data[0].cid }&pageIndex=${1 }">首页 </a></li>
                                                    	<li><a href="${pageContext.request.contextPath }/title.do?type=category&id=${pageInfo.data[0].cid }&pageIndex=${pageInfo.pageIndex-1 }">&lt;&lt; 上一页 </a></li>
                                                    </c:if>
                                                    
                                                    <c:if test="${pageInfo.totalPages <= 10 }">
                                                    	<c:set var="startIndex" value="${1 }"></c:set>
														<c:set var="endIndex" value="${pageInfo.totalPages }"></c:set>
                                                    </c:if>
                                                    <c:if test="${pageInfo.totalPages > 10 }">
	                                                    <c:set var="startIndex" value="${pageInfo.pageIndex - 5 }"></c:set>
														<c:set var="endIndex" value="${startIndex + 9 }"></c:set>
														<c:if test="${startIndex <= 0 }">
															<c:set var="startIndex" value="${1 }"></c:set>
															<c:set var="endIndex" value="${startIndex + 9 }"></c:set>
														</c:if>
														<c:if test="${endIndex > pageInfo.totalPages }">
															<c:set var="endIndex" value="${pageInfo.totalPages }"></c:set>
															<c:set var="startIndex" value="${endIndex - 9 }"></c:set>
														</c:if>
													</c:if>
													<c:forEach begin="${startIndex }" end="${endIndex }" var="i">
														<c:if test="${i == pageInfo.pageIndex + 1 }">
                                                    		<li class="currentpage">${i }</li>
                                                    	</c:if>
                                                    	<c:if test="${!(i == pageInfo.pageIndex + 1) }">
                                                    		<li><a href="${pageContext.request.contextPath }/title.do?type=category&id=${pageInfo.data[0].cid }&pageIndex=${i }">${i }</a></li>
                                                    	</c:if>
                                                    </c:forEach>
                                                    
                                                    
                                                    <c:if test="${pageInfo.isLastPage }">
                                                    	<li class="disablepage">下一页 >></li>
                                                    	<li class="disablepage">末页 </li>
                                                    </c:if>
                                                    <c:if test="${!pageInfo.isLastPage }">
														<li class="nextpage"><a href="${pageContext.request.contextPath }/title.do?type=category&id=${pageInfo.data[0].cid }&pageIndex=${pageInfo.pageIndex+1 }">下一页 >></a></li>
														<li class="nextpage"><a href="${pageContext.request.contextPath }/title.do?type=category&id=${pageInfo.data[0].cid }&pageIndex=${pageInfo.totalPages }">末页 </a></li>
                                                    </c:if>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>

	<!-- 导入footer.jsp -->
	<%@include file="footer.jsp" %>
</body>
</html>