$(function(){
	$('#userTab').datagrid({
		url:'/eShop/user.do',
		queryParams:{type:'select'},
		title:'用户信息',
		iconCls:'icon-man',
		rownumbers:true,
		collapsible:true,
		singleSelect:true,
		idField:'userid',
		toolbar:[
			{text:'修改',iconCls:'icon-edit',handler:function(){
				var row = $('#userTab').datagrid('getSelected');
				if (row == null) {
					$.messager.alert('错误','请选择要修改的数据行','warning');
				} else {
					$("#uemail").textbox('setValue',row.uemail);
					$("#uloginid").textbox('setValue',row.uloginid);
					$("#uloginid").textbox('readonly');
					$("#upassword").textbox('setValue',row.upassword);
					$("#uaddress").textbox('setValue',row.uaddress);
					$("#utel").textbox('setValue',row.utel);
					$("#usex").combobox('setValue',row.usex);
					$('#add').dialog({
						closed:false,
						title:'编辑数据',
						collapsible:true,
						iconCls:'icon-man',
						buttons:[{
							text:'确认修改',
							iconCls:'icon-ok',
							handler:function(){
								var isValid = $('form').form('validate');
								if (isValid) {
									var datas = $('form').serialize() + '&type=update';
									$.ajax({
										type:'post',
										url:'/eShop/user.do',
										data:datas,
										success:function(data){
											if (data == '1') {
												$.messager.alert('修改成功','用户信息修改成功','info');
												$('#addUser').form('clear');
												$('#add').dialog('close');
												$('#userTab').datagrid('reload');
											} else{
												$.messager.alert('修改失败','请重新修改或联系管理员','error');
											}
										}
									})
								} else {
									$.messager.alert('修改失败','有所填项未通过验证','info');
								}
							}
						},{
							text:'取消',
							iconCls:'icon-cancel',
							handler:function(){
								$('#add').dialog('close');
							}
						}]
					})
				}
			}},'-',
			{text:'添加',iconCls:'icon-add',handler:function(){
				$('#addUser').form('clear');
				$("#uloginid").textbox('readonly',false);
				$('#add').dialog({
					closed:false,
					title:'添加用户',
					collapsible:true,
					iconCls:'icon-man',
					buttons:[{
						text:'确认添加',
						iconCls:'icon-ok',
						handler:function(){
							var isValid = $('form').form('validate');
							if (isValid) {
								var datas = $('form').serialize() + '&type=insert';
								$.ajax({
									type:'post',
									url:'/eShop/user.do',
									data:datas,
									success:function(data){
										if (data == '1') {
											$.messager.alert('添加成功','用户添加成功','info');
											$('#addUser').form('clear');
											$('#add').dialog('close');
											$('#userTab').datagrid('reload');
										} else{
											$.messager.alert('添加失败','添加失败：请检查账号是否已存在后重新添加或联系运维','error');
										}
									}
								})
							} else {
								$.messager.alert('添加失败','有所填项未通过验证','info');
							}
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$('#add').dialog('close');
						}
					}]
				})
			}},'-',
			{text:'删除',iconCls:'icon-remove',handler:function(){
				var row = $('#userTab').datagrid('getSelected');
				$.messager.confirm('删除确认', '您确认要删除用户：'+row.uloginid+'的所有数据吗？', function(r){
					if (r){
						$.ajax({
							url:'/eShop/user.do',
							type:'post',
							data:{userid:row.userid,type:'remove'},
							success:function(data){
								if (data == '1') {
									$.messager.alert('删除提示',row.userid+'删除成功','info');
									$('#userTab').datagrid('reload');
								}
							}
						});
					}
				});
			}},'-',
			{text:'模糊查找：<input id="searchinput" class="easyui-searchbox" style="width:150px" menu="#userTab" searcher:"dosearch">',handler:function(){
			}}
		],
		columns:[[
			{field:'chk',checkbox:true},
			{field:'userid',title:'用户ID'},
			{field:'uemail',title:'邮箱'},
			{field:'uloginid',title:'账号'},
			{field:'upassword',title:'密码'},
			{field:'usex',title:'性别'},
			{field:'uaddress',title:'发货地址'},
			{field:'utel',title:'联系方式'},
			{field:'ustateid',title:'状态码'},
			{field:'uroleid',title:'权限码'},
			{field:'uemailstateid',title:'邮箱验证状态码'}
		]]
	});
	$('#searchinput').on('input',function(){
		dosearch();
	})
})
function dosearch(){
	var inputs = $('#searchinput').val();
	$('#userTab').datagrid('load',{
		type:'search',
		inputs:inputs
	});
	$('#userTab').datagrid('reload');
}