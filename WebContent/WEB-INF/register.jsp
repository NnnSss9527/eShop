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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/registercheckjQuery.js"></script>
<script type="text/javascript">
	window.onload = function() {
		document.querySelector('#checkCodeImg').onclick=function(){
			this.src='${pageContext.request.contextPath }/title.do?type=checkCode&random='+Math.random();
		}
	}
</script>
</head>
<body class="main">
<!-- 导入header.jsp -->
<%@include file="header.jsp" %>

<form action="${pageContext.request.contextPath }/title.do?type=register" method="post">
	<div id="divcontent">
	<table width="850px" border="0" cellspacing="0">
	  <tr>
	    <td style="padding:30px"><h1>新会员注册</h1>
	<table width="70%" border="0" cellspacing="2" class="upline">
	  <tr>
	    <td style="text-align:right; width:20%">会员邮箱：</td>
	    <td style="width:40%"><input type="text" id="uemail" name="uemail" class="textinput"/></td>
	    <td><span id="uemailspan"/></td>
	  </tr>
	  <tr>
	    <td style="text-align:right">会员名：</td>
	    <td><input type="text" name="uname" id="uname" class="textinput"/></td>
	    <td><span id="unamespan"/></td>
	  </tr>
	  <tr>
	    <td style="text-align:right">密码：</td>
	    <td><input type="password" name="upassword" class="textinput"/></td>
	    <td><font color="#999999">密码设置至少6位，请区分大小写</font></td>
	  </tr>
	  <tr>
	    <td style="text-align:right">重复密码：</td>
	    <td><input type="password" name="urepassword" class="textinput"/></td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td style="text-align:right">性别：</td>
	    <td colspan="2">&nbsp;&nbsp;<input type="radio" name="usex" value="男" />
	      男
	        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="usex" value="女" />
	女</td>
	    </tr>
	  <tr>
	    <td style="text-align:right">联系方式：</td>
	    <td colspan="2"><input name="utel" type="text" class="textinput"/></td>
	  </tr>
	  <tr>
	    <td style="text-align:right">发货地址：</td>
	    <td colspan="2"><input name="uaddress" type="text" class="textinput" style="width:350px"/></td>
	    </tr>
	  <tr>
	    <td style="text-align:right">&nbsp;</td>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	  </tr>
	</table>
	
	<h1>密码保护信息 (以下信息用于取回密码以及处理其他帐户问题，请您慎重填写并牢记)</h1>
	<table width="70%" border="0" cellspacing="2" class="upline">
	  <tr>
	    <td width="16%" style="text-align:right; width:20%">密码保护问题：</td>
	    <td width="47%" style="width:50%"><select name="question" class="textinput" style="width:230px; height:20px">
	<option value="">- 选择一个问题 -</option>
	<option value="您的宠物的名字？" >您的宠物的名字？</option>
	<option value="您就读的第一所学校的名称？" >您就读的第一所学校的名称？</option>
	<option value="少年时代心目中的英雄是谁？" >少年时代心目中的英雄是谁？</option>
	<option value="您最喜欢的休闲运动是什么？" >您最喜欢的休闲运动是什么？</option>
	<option value="您最喜欢哪支运动队？" >您最喜欢哪支运动队？</option>
	<option value="您最喜欢的运动员是谁？" >您最喜欢的运动员是谁？</option>
	<option value="您的第一辆汽车或自行车是什么牌子的？" >您的第一辆汽车或自行车是什么牌子的？</option>
	    </select>    </td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td style="text-align:right">答案：</td>
	    <td colspan="2"><input type="text" name="answer" class="textinput"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      <font color="#999999">不能少于4个字符，不区分大小写</font></td>
	    </tr>
	    <tr>
	    <td style="text-align:right">备用邮箱：</td>
	    <td><input type="text" name="email" class="textinput"/></td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td style="text-align:right">&nbsp;</td>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	  </tr>
	</table>
	
	<h1>注册校验</h1>
	<table width="80%" border="0" cellspacing="2" class="upline">
	  <tr>
	    <td style="text-align:right; width:20%">输入校验码：</td>
	    <td style="width:30%"><input type="text" name="inputCodes" class="textinput"/></td>
	    <td><img src="${pageContext.request.contextPath }/title.do?type=checkCode" id="checkCodeImg" alt="" />&nbsp;</td>
	  </tr>
	  <tr>
	    <td style="text-align:right">&nbsp;</td>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	  </tr>
	</table>
	
	<h1>服务条款</h1>
	<table width="70%" border="0" cellspacing="0">
	  <tr>
	    <td><textarea name="textarea" class="textarea" ></textarea></td>
	  </tr>
	</table>
	
	<table width="70%" border="0" cellspacing="0">
	  <tr>
	    <td style="padding-top:20px; text-align:center">
			<input type="image" id="register" src="${pageContext.request.contextPath }/images/signup.gif" />
	    </td>
	  </tr>
	</table></td>
	    </tr>
	</table>
	</div>
</form>

<!-- 导入footer.jsp -->
<%@include file="footer.jsp" %>
</body>
</html>