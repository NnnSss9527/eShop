window.onload = function() {
	document.querySelector('#search').oninput = function() {
		var search = this.value;
		var xmlHttpRequest = new XMLHttpRequest();
		xmlHttpRequest.open("POST","title.do",true);
		var data = "type=searchdatalist&search="+search;
		xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		xmlHttpRequest.send(data);
		xmlHttpRequest.onreadystatechange = function(){
			if (xmlHttpRequest.readyState == 4) {
				if (xmlHttpRequest.status == 200) {
					var data = xmlHttpRequest.responseText;
					var datas = JSON.parse(data);
					var options = "";
					if (datas != null) {
						for (var i = 0; i < datas.length; i++) {
							options += "<option value='"+datas[i].gtitle+"'/>";
						}
					}
					var datalists = document.querySelector('#datalists');
					datalists.innerHTML = options;
				}
			}
		}
	}
	//验证码刷新
	document.querySelector('#checkCodeImg').onclick=function(){
		this.src='${pageContext.request.contextPath }/title.do?type=checkCode&random='+Math.random();
	}
}