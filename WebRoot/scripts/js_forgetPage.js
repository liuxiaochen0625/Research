$(function(){
	$("#send").click(function(){
		$(".error").remove();
		$(".righta").remove();
		var mail=$(".mail").val();
		 var reg= /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if(reg.test(mail)){
			//进行ajax提交,找邮箱,如果存在,就发送邮箱,不存在的话就返回信息
			$.ajax({
	            url: "e-mail/mail_exits.action",
	            // 数据发送方式
	            type: "post",
	            // 接受数据格式
	            dataType : "json",
	            // 要传递的数据
	          	data : "mail="+mail,
	            // 回调函数，接受服务器端返回给客户端的值，即result值
	            success :function(result){
	            	var json=eval("("+result+")");
	            	if(json.exits=='no'){//数据库中没有这个邮箱,返回错误信息
	            		var info=$("<span class='a2 error'>邮箱未注册</span>")
	            		$(".forget_form").append(info);
	            	}else{//数据库中有这个邮箱,进行ajax发送数据
	            		//首先处理前台界面的一些信息,
	            		$(".label_mail").remove();
	            		$(".mail").remove();
	            		$(".send").remove();
	            		var info=$("<span class='a2 righta'>邮箱已经发送至"+mail+",请及时查收</span>")
	            		$(".forget_form").append(info);
	            		//然后ajax发送
	            		$.ajax({
	        	            url: "e-mail/mail_sendPassMail.action",
	        	            // 数据发送方式
	        	            type: "post",
	        	            // 接受数据格式
	        	            dataType : "json",
	        	            // 要传递的数据
	        	          	data : "mail="+mail,
	        	        })
	            	}
	            }
		   });
		}else{
			var info=$("<span class='a2 error'>邮箱格式不正确</span>")
			$(".forget_form").append(info);
		}
	})
})