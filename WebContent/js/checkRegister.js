window.onload = function(){
	//用户名的Ajax验证
	document.querySelector('#uname').oninput = function(){
		alert('11111');
		var uname = this.value;
		var xmlHttpRequest = new XMLHttpRequest();
		xmlHttpRequest.open("POST","title.do",true);
		var data = "type=unamecheck&uname="+uname;
		xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		xmlHttpRequest.send(data);
		xmlHttpRequest.onreadystatechange = function(){
			if (xmlHttpRequest.readyState == 4) {
				if (xmlHttpRequest.status == 200) {
					var data = xmlHttpRequest.responseText;
					var unamespan = document.querySelector('#unamespan');
					if (data == '1') {
						unamespan.innerHTML = '账号已被占用';
						unamespan.style.color = 'red';
					}
					if (data == '0') {
						unamespan.innerHTML = '账号可用';
						unamespan.style.color = 'green';
					}
				}
			}
		}
	}
	//邮箱的Ajax验证
	document.querySelector('#uemail').oninput = function(){
		var uemail = this.value;
		var xmlHttpRequest = new XMLHttpRequest();
		xmlHttpRequest.open("POST","title.do",true);
		var data = "type=uemailcheck&uemail="+uemail;
		xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		xmlHttpRequest.send(data);
		xmlHttpRequest.onreadystatechange = function(){
			if (xmlHttpRequest.readyState == 4) {
				if (xmlHttpRequest.status == 200) {
					var data = xmlHttpRequest.responseText;
					var uemailspan = document.querySelector('#uemailspan');
					if (data == '1') {
						uemailspan.innerHTML = '邮箱已存在';
						uemailspan.style.color = 'red';
					}
					if (data == '0') {
						uemailspan.innerHTML = '邮箱可用';
						uemailspan.style.color = 'green';
					}
				}
			}
		}
	}
	//验证码刷新
	document.querySelector('#checkCodeImg').onclick=function(){
		this.src='${pageContext.request.contextPath }/title.do?type=checkCode&random='+Math.random();
	}
}