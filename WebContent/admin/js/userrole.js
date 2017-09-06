$(function(){
	$('#urolename').combobox({
		url:'/eShop/userrole.do?type=select',
		valueField:'uroleid',
		textField:'urolename',
		onLoadSuccess:function(){
			var data = $(this).combobox('getData');
			$(this).combobox('setValue',data[0].uroleid);
		},
		onSelect:function(data){
		}
	});
	$('#userroleTab').datagrid({
		url:'/eShop/userrole.do',
		queryParams:{type:'selected'},
		title:'权限信息',
		iconCls:'icon-man',
		rownumbers:true,
		collapsible:true,
		singleSelect:true,
		idField:'uroleid',
		toolbar:[
			{text:'修改',iconCls:'icon-edit',handler:function(){
				var row = $('#userroleTab').datagrid('getSelected');
				if (row == null) {
					$.messager.alert('错误','请选择要修改的数据行','warning');
				} else {
					$("#uloginid").textbox('setValue',row.uloginid);
					$("#uloginid").textbox('readonly');
					$("#urolename").combobox('setValue',row.urolename);
					$('#add').dialog({
						closed:false,
						title:'权限修改',
						collapsible:true,
						iconCls:'icon-man',
						buttons:[{
							text:'确认修改',
							iconCls:'icon-ok',
							handler:function(){
								var isValid = $('form').form('validate');
								if (isValid) {
									var datas = $('form').serialize() + '&type=update' + '&userid=' +row.userid;
									$.ajax({
										type:'post',
										url:'/eShop/userrole.do',
										data:datas,
										success:function(data){
											if (data == '1') {
												$.messager.alert('修改成功','用户权限修改成功','info');
												$('#addUser').form('clear');
												$('#add').dialog('close');
												$('#userroleTab').datagrid('reload');
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
			{text:'模糊查找：<input id="searchinput" class="easyui-searchbox" style="width:150px" menu="#userroleTab" searcher:"dosearch">',handler:function(){
			}}
		],
		columns:[[
			{field:'chk',checkbox:true},
			{field:'uloginid',title:'账号'},
			{field:'userid',title:'用户ID'},
			{field:'uroleid',title:'权限',formatter: function(value,row,index){return row.urolename}}
		]]
	});
	$('#searchinput').on('input',function(){
		dosearch();
	})
})
function dosearch(){
	var inputs = $('#searchinput').val();
	$('#userroleTab').datagrid('load',{
		type:'search',
		inputs:inputs
	});
	$('#userroleTab').datagrid('reload');
}