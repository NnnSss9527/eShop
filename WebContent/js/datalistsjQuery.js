$(function() {
	$('#search').on('input',function(){
		$.ajax({
			url:'title.do',
			data:{type:'searchdatalist',search:$(this).val()},
			dataType:'json',
			type:'post',
			success:function(data){
				$('#datalists').empty();
				$(data).each(function(index,goods){
					$('#datalists').append("<option value='"+goods.gtitle+"'/>")
				})
			}
		})
	})
})