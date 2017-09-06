$(function(){
	$('#page').pagination({
		pageSize:5,
		firstBtnText:'首页',
		lastBtnText:'末页',
		prevBtnText:'上一页',
		nextBtnText:'下一页',
		showInfo:true,
		remote:{
			url:'title.do?type=pagination',
			totalName:'totalNumber',
			success:function(json){
				$('#content').empty();
				var ul = '<ul>';
				$(json.data).each(function(index,goods){
					ul += '<li>' + goods.gtitle + '</li>';
				})
				ul += '</ul>';
				$('#content').append(ul);
			}
		}
	});
})