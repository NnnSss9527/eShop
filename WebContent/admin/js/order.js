$(function(){
	$('#orderTab').datagrid({
		fitColumns:true,
		url:'/eShop/order.do',
		queryParams:{type:'selected'},
		title:'订单管理',
		iconCls:'icon-man',
		rownumbers:true,
		collapsible:true,
		singleSelect:true,
		idField:'orderid',
		columns:[[
			{field:'chk',checkbox:true},
			{field:'orderid',title:'总订单编号'},
			{field:'userid',title:'用户ID'},
			{field:'totalprice',title:'支付金额'},
			{field:'orderdate',title:'订单日期'}
		]],
		view: detailview,
		detailFormatter: function(index, row){
			return '<div style="padding:2px"><table id="orderdetail-' + index + '"></table></div>';
		},
		 onExpandRow:function(index,row){
			 $('#orderdetail-'+index).datagrid({
				 url:'/eShop/orderdetail.do',
				 queryParams:{type:'selected',orderid:row.orderid},
				 rownumbers:true,
				 collapsible:true,
				 fitColumns:true,
				 singleSelect:true,
				 idField:'orderid',
				 columns:[[
				 	{field:'chk',checkbox:true},
					{field:'orderdetailid',title:'分订单编号'},
					{field:'gid',title:'商品编号'},
					{field:'gtitle',title:'商品名称'},
					{field:'ginprice',title:'单品售价'},
					{field:'gnumber',title:'数量'},
					{field:'orderid',title:'订单编号'}
				 ]],
				 onResize:function(){  
                     $('#orderTab').datagrid('fixDetailRowHeight',index);  
                 },  
                 onLoadSuccess:function(){  
                     setTimeout(function(){  
                         $('#orderTab').datagrid('fixDetailRowHeight',index);  
                     },0);  
                 }
			 });
//			 $('#orderTab').datagrid('fixDetailRowHeight',index);
		 }
	});
	$('#searchinput').on('input',function(){
		dosearch();
	})
})
function dosearch(){
	var inputs = $('#searchinput').val();
	$('#orderTab').datagrid('load',{
		type:'search',
		inputs:inputs
	});
	$('#orderTab').datagrid('reload');
}