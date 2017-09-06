$(function(){
	$('#login').dialog({
		width:400,
		height:200,
		title:'用户登录',
		collapsible:true,
		iconCls:'icon-man',
		buttons:[{
			text:'登录',
			iconCls:'icon-ok',
			handler:function(){
				var isValid = $('form').form('validate');
				if (isValid) {
					$.ajax({
						type:'post',
						url:'/eShop/login.do',
						data:$('form').serialize(),
						success:function(data){
							if (data == '1') {
								window.location = 'index.html';
							} else if (data == '0'){
								$.messager.alert('登陆失败','账号或密码错误，请检查是否拼写正确','info');
							} else if (data == '2'){
								$.messager.alert('登陆失败','账号被锁定，请联系管理员。如果您已是管理员，请联系运维。','info');
							} else{
								$.messager.alert('登陆失败','该账号没有管理员权限！','info');
							}
						}
					})
				} else {
					$.messager.alert('登陆失败','账号或密码未通过验证','info');
				}
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){}
		}]
	})
})