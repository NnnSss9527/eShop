$(function() {
	//用户名的Ajax验证
	$('#uname').on('input',function(){
		$.ajax({
			data:{'type':'unamecheck','uname':$(this).val()},
			dataType:'text',
			type:'POST',
			url:'title.do',
			success:function(data){
				if (data == '1') {
					$('#unamespan').html('账号已被占用');
					$('#unamespan').css('color','red');
				}
				if (data == '0') {
					$('#unamespan').html('账号可用');
					$('#unamespan').css('color','green');
				}
			}
		});
	})
	//邮箱的Ajax验证
	$('#uemail').on('input',function(){
		$.ajax({
			data:{'type':'uemailcheck','uemail':$(this).val()},
			dataType:'text',
			type:'POST',
			url:'title.do',
			success:function(data){
				if (data == '1') {
					$('#uemailspan').html('邮箱已注册');
					$('#uemailspan').css('color','red');
				}
				if (data == '0') {
					$('#uemailspan').html('邮箱可用');
					$('#uemailspan').css('color','green');
				}
			}
		});
	})
//	$('#register').on('click',function(){
//		alert($('form').serialize());
//		alert($('form').serializeArray());
//	})
})