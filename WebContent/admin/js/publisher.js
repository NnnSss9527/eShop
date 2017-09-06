$(function(){
	$('#publisherTab').datagrid({
		url:'/eShop/publisher.do',
		queryParams:{type:'select'},
		title:'出版社信息',
		iconCls:'icon-man',
		rownumbers:true,
		collapsible:true,
		singleSelect:true,
		idField:'userid',
		toolbar:[
			{text:'修改',iconCls:'icon-edit',handler:function(){
				var row = $('#publisherTab').datagrid('getSelected');
				if (row == null) {
					$.messager.alert('错误','请选择要修改的数据行','warning');
				} else {
					if (row.pid == '78932ab0-c11a-4a3b-ad1b-f830c75fc6e4') {
						$.messager.alert('提示','该行不允许修改！','info');
					} else {
						$("#pid").textbox('setValue',row.pid);
						$("#pid").textbox('readonly');
						$("#pname").textbox('setValue',row.pname);
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
											url:'/eShop/publisher.do',
											data:datas,
											success:function(data){
												if (data == '1') {
													$.messager.alert('修改成功','出版社信息修改成功','info');
													$('#addUser').form('clear');
													$('#add').dialog('close');
													$('#publisherTab').datagrid('reload');
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
				$("#pid").textbox('readonly',false);
				$('#add').dialog({
					closed:false,
					title:'添加出版社',
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
									url:'/eShop/publisher.do',
									data:datas,
									success:function(data){
										if (data == '1') {
											$.messager.alert('添加成功','出版社添加成功。','info');
											$('#addUser').form('clear');
											$('#add').dialog('close');
											$('#publisherTab').datagrid('reload');
										} else{
											$.messager.alert('添加失败','添加失败请检查出版社编号是否已存在后重新添加或联系运维','error');
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
				var row = $('#publisherTab').datagrid('getSelected');
				if (row.pid == '78932ab0-c11a-4a3b-ad1b-f830c75fc6e4') {
					$.messager.alert('提示','该行不允许删除！','info');
				} else {
					$.messager.confirm('删除确认', '您确认要删除'+row.pname+'吗？', function(r){
						if (r){
							$.ajax({
								url:'/eShop/publisher.do',
								type:'post',
								data:{pid:row.pid,type:'remove'},
								success:function(data){
									if (data == '1') {
										$.messager.alert('删除提示',row.pname+'删除成功','info');
										$('#publisherTab').datagrid('reload');
									}
								}
							});
						}
					});
				}
			}},'-',
			{text:'模糊查找：<input id="searchinput" class="easyui-searchbox" style="width:150px" menu="#publisherTab" searcher:"dosearch">',handler:function(){
			}}
		],
		columns:[[
			{field:'chk',checkbox:true},
			{field:'pid',title:'出版社编号'},
			{field:'pname',title:'出版社名称'},
		]]
	});
	$('#searchinput').on('input',function(){
		dosearch();
	})
})
function dosearch(){
	var inputs = $('#searchinput').val();
	$('#publisherTab').datagrid('load',{
		type:'search',
		inputs:inputs
	});
	$('#publisherTab').datagrid('reload');
}