$(function(){
	$('#ustatename').combobox({
		url:'/eShop/userstate.do?type=select',
		valueField:'ustateid',
		textField:'ustatename',
		onLoadSuccess:function(){
			var data = $(this).combobox('getData');
			$(this).combobox('setValue',data[0].ustateid);
		},
		onSelect:function(data){
		}
	});
	$('#userstateTab').datagrid({
		url:'/eShop/userstate.do',
		queryParams:{type:'selected'},
		title:'账号状态信息',
		iconCls:'icon-man',
		rownumbers:true,
		collapsible:true,
		singleSelect:true,
		idField:'ustateid',
		toolbar:[
			{text:'修改',iconCls:'icon-edit',handler:function(){
				var row = $('#userstateTab').datagrid('getSelected');
				if (row == null) {
					$.messager.alert('错误','请选择要修改的数据行','warning');
				} else {
					$("#uloginid").textbox('setValue',row.uloginid);
					$("#uloginid").textbox('readonly');
					$("#ustatename").combobox('setValue',row.ustatename);
					$('#add').dialog({
						closed:false,
						title:'状态修改',
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
										url:'/eShop/userstate.do',
										data:datas,
										success:function(data){
											if (data == '1') {
												$.messager.alert('修改成功','用户状态修改成功','info');
												$('#addUser').form('clear');
												$('#add').dialog('close');
												$('#userstateTab').datagrid('reload');
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
			{text:'模糊查找：<input id="searchinput" class="easyui-searchbox" style="width:150px" menu="#userstateTab" searcher:"dosearch">',handler:function(){
			}}
		],
		columns:[[
			{field:'chk',checkbox:true},
			{field:'uloginid',title:'账号'},
			{field:'userid',title:'用户ID'},
			{field:'ustateid',title:'账号状态',formatter: function(value,row,index){return row.ustatename}}
		]]
	});
	$('#searchinput').on('input',function(){
		dosearch();
	})
})
function dosearch(){
	var inputs = $('#searchinput').val();
	$('#userstateTab').datagrid('load',{
		type:'search',
		inputs:inputs
	});
	$('#userstateTab').datagrid('reload');
}