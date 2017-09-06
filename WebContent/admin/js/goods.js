$(function() {
	$("#gclicks").textbox('readonly');
	$("#gid").textbox('readonly');
	$('#btn').on('click',function(){
		var file = $('#gimg').filebox('getValue');
		if (file != '') {
			if (file.endsWith('.jpg') || file.endsWith('.png') || file.endsWith('.jpeg')) {
				var filename = file.substring(file.lastIndexOf("\\")+1, file.size);
				$.ajax({
					type:'post',
					url:'/eShop/goods.do',
					data:{type:'uploadImg'},
					contentType:false,
//					success:function(data){
//						$.messager.alert('info','图片上传成功','info');
//					}
				})
			} else {
				$.messager.alert('warning','请选择.jpg、.png、.jpeg格式的图片','warning');
			}
		} else {
			$.messager.alert('error','请选择要上传的图片','error');
		}
	})
	$('#category').combobox({
		url:'/eShop/category.do?type=select',
		valueField:'cid',
		textField:'cname',
		onLoadSuccess:function(){
			var data = $(this).combobox('getData');
			$(this).combobox('setValue',data[0].cid);
			var cid = $(this).combobox('getValue');
			loadGoods(cid);
		},
		onSelect:function(data){
			loadGoods(data.cid);
		}
	});
	$('#cname').combobox({
		url:'/eShop/category.do?type=select',
		valueField:'cid',
		textField:'cname',
		onLoadSuccess:function(){
			var data = $(this).combobox('getData');
			$(this).combobox('setValue',data[0].cid);
		},
		onSelect:function(data){
		}
	});
	$('#pname').combobox({
		url:'/eShop/publisher.do?type=select',
		valueField:'pid',
		textField:'pname',
		onLoadSuccess:function(){
			var data = $(this).combobox('getData');
			$(this).combobox('setValue',data[0].pid);
		},
		onSelect:function(data){
		}
	});
	$.extend($.fn.validatebox.defaults.rules, {  
		//验证商品编号  
//		GidIsExist: {  
//	        validator: function (value) {
//	        	var isExist = $.ajax({url:'/eShop/goods.do',type:'post',async:false,cache:false,data:{type:'find',gid:value}}).responseText;
//	        	return isExist === '0';
//	        },  
//	        message: '商品编号已存在，请重新输入.'  
//		},
		//验证价格
		Price: {  
			validator: function (value) {  
				var reg =/^(\d+\.\d{1,2})$/;  
				return reg.test(value);
			},  
			message: '请输入数字，小数点后最多两位,不足两位用0补齐.'  
		}
	})
})
function loadGoods(cid){
	$('#goodsTab').datagrid({
		url:'/eShop/goods.do',
		title:'图书信息',
		rownumbers:true,
		singleSelect:true,
		queryParams:{cid:cid,type:'select'},
		pagination:true,
		nowrap:false,
		columns:[[
			{field:'chk',checkbox:true},
			{field:'gid',title:'商品编号'},
			{field:'gtitle',title:'书名'},
			{field:'gauthor',title:'作者'},
			{field:'gsaleprice',title:'售价'},
			{field:'ginprice',title:'优惠价'},
			{field:'gdesc',title:'简介',hidden:true},
			{field:'gimg',title:'封面',formatter:function(value,row,index){return '<img width="100px" height="100px" src="/eShop/images/bookcover/'+row.gimg+'"/>'}},
			{field:'gclicks',title:'点击量'},
			{field:'cid',title:'类别',formatter: function(value,row,index){return row.category.cname}},
			{field:'pid',title:'出版社',formatter: function(value,row,index){return row.publisher.pname}}
		]],
		toolbar:[
			{text:'添加',iconCls:'icon-add',handler:function(){
				$("#gid").textbox('setValue','系统自动生成,无需输入');
				$("#gclicks").textbox('setValue','0');
				$('#add').dialog({
					closed:false,
					title:'添加图书',
					collapsible:true,
					iconCls:'icon-sum',
					buttons:[{
						text:'确认添加',
						iconCls:'icon-ok',
						handler:function(){
							var isValid = $('form').form('validate');
							if (isValid) {
								var datas = $('form').serialize() + '&type=insert';
								console.log(datas);
								$.ajax({
									type:'post',
									url:'/eShop/goods.do',
									data:datas,
									success:function(data){
										if (data == '1') {
											$.messager.alert('添加成功','商品添加成功','info');
											$('#addUser').form('clear');
											$('#add').dialog('close');
											$('#um').datagrid('reload');
										} else{
											$.messager.alert('添加失败','添加失败：请检查商品编号是否与已有的重复后再重新添加或联系运维','error');
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
				var row = $('#goodsTab').datagrid('getSelected');
				if (row != null) {
					$.messager.confirm('删除确认', '您确认要删除这本书的所有数据吗？一旦删除将不能恢复！', function(r){
						if (r){
							$.ajax({
								url:'/eShop/goods.do',
								type:'post',
								data:{gid:row.gid,type:'remove'},
								success:function(data){
									if (data == '1') {
										$.messager.alert('删除提示!删除成功!','info');
										$('#goodsTab').datagrid('reload');
									}
								}
							});
						}
					});
				}
		}},'-',
			{text:'修改',iconCls:'icon-edit',handler:function(){
				var row = $('#goodsTab').datagrid('getSelected');
				if (row == null) {
					$.messager.alert('错误','请选择要修改的数据行','warning');
				} else {
					$("#gid").textbox('setValue',row.gid);
					$("#gtitle").textbox('setValue',row.gtitle);
					$("#gauthor").textbox('setValue',row.gauthor);
					$("#gsaleprice").textbox('setValue',row.gsaleprice);
					$("#ginprice").textbox('setValue',row.ginprice);
					$("#gclicks").textbox('setValue',row.gclicks);
					$("#cname").combobox('setValue',row.cid);
					$("#pname").combobox('setValue',row.pid);
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
										url:'/eShop/goods.do',
										data:datas,
										success:function(data){
											if (data == '1') {
												$.messager.alert('修改成功','商品信息修改成功','info');
												$('#addUser').form('clear');
												$('#add').dialog('close');
												$('#goodsTab').datagrid('reload');
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
			{text:'模糊查找：<input id="searchinput" class="easyui-searchbox" style="width:150px" menu="#goodsTab" searcher:"dosearch" >',handler:function(){
		}}
		]
	});
	$('#searchinput').on('input',function(){
		dosearch(cid);
	})
}
function dosearch(cid){
	var inputs = $('#searchinput').val();
	if (inputs != null) {
		$('#goodsTab').datagrid('load',{
			type:'search',
			inputs:inputs,
			cid:cid
		});
	}
	$('#goodsTab').datagrid('reload');
}


//,formatter:function(value,row,index){   //关键：格式化，并返回一个img标签
//    return '<img width="100px" height="100px" src="/eShop/images/bookcover/'+row.gimg+'.jpg"/>'
//}
