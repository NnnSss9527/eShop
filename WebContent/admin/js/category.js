$(function(){
	$('#categoryTab').datagrid({
		url:'/eShop/category.do',
		queryParams:{type:'select'},
		title:'图书类别信息',
		iconCls:'icon-man',
		rownumbers:true,
		collapsible:true,
		singleSelect:true,
		idField:'userid',
		toolbar:[
			{text:'修改',iconCls:'icon-edit',handler:function(){
				var row = $('#categoryTab').datagrid('getSelected');
				if (row == null) {
					$.messager.alert('错误','请选择要修改的数据行','warning');
				} else {
					if (row.cid == '8a99ab15-c4a7-42f6-8a25-6160725b9f1a') {
						$.messager.alert('提示','选择的行是未分类图书所在的类别，该类别不允许修改！','info');
					} else {
						$("#cid").textbox('setValue',row.cid);
						$("#cid").textbox('readonly');
						$("#cname").textbox('setValue',row.cname);
						$('#add').dialog({
							closed:false,
							title:'类别信息修改',
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
											url:'/eShop/category.do',
											data:datas,
											success:function(data){
												if (data == '1') {
													$.messager.alert('修改成功','图书类别信息修改成功','info');
													$('#addUser').form('clear');
													$('#add').dialog('close');
													$('#categoryTab').datagrid('reload');
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
				}
			}},'-',
			{text:'添加',iconCls:'icon-add',handler:function(){
				$('#addUser').form('clear');
				$("#cid").textbox('readonly',false);
				$('#add').dialog({
					closed:false,
					title:'添加图书类别',
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
									url:'/eShop/category.do',
									data:datas,
									success:function(data){
										if (data == '1') {
											$.messager.alert('添加成功','图书类别添加成功，快去添加新商品吧。','info');
											$('#addUser').form('clear');
											$('#add').dialog('close');
											$('#categoryTab').datagrid('reload');
										} else{
											$.messager.alert('添加失败','添加失败请检查类别编号是否已存在后重新添加或联系运维','error');
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
				var row = $('#categoryTab').datagrid('getSelected');
				if (row.cid == '8a99ab15-c4a7-42f6-8a25-6160725b9f1a') {
					$.messager.alert('提示','选择的行是“未分类”图书所在的类别，该类别禁止删除！','info');
				} else {
					$.messager.confirm('删除确认', '您确认要删除'+row.cname+'类吗？删除后，当前类别下的图书将移动至“未分类”目录下。', function(r){
						if (r){
							$.ajax({
								url:'/eShop/category.do',
								type:'post',
								data:{cid:row.cid,type:'remove'},
								success:function(data){
									if (data == '1') {
										$.messager.alert('删除提示',row.cname+'删除成功','info');
										$('#categoryTab').datagrid('reload');
									}
								}
							});
						}
					});
				}
			}},'-',
			{text:'模糊查找：<input id="searchinput" class="easyui-searchbox" style="width:150px" menu="#categoryTab" searcher:"dosearch">',handler:function(){
			}}
		],
		columns:[[
			{field:'chk',checkbox:true},
			{field:'cid',title:'类别编号'},
			{field:'cname',title:'类别名称'},
		]]
	});
	$('#searchinput').on('input',function(){
		dosearch();
	})
})
function dosearch(){
	var inputs = $('#searchinput').val();
	$('#categoryTab').datagrid('load',{
		type:'search',
		inputs:inputs
	});
	$('#categoryTab').datagrid('reload');
}