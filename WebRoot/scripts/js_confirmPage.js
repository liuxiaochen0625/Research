/**
**采用ajax方式重发邮件,刷新整个页面
*/
$(function(){
	/**
	 * 如果点击重发按钮,就ajax方式异步加载
	 */
	$("#a_resend").click(function(){
		$("#resend_checknumber").remove();
		var html=$("<form action='' id='resend_checknumber' class='resend_checknumber'>"
						+"<div class='checknumber_title'>"
							+"<h3>请输入验证码</h3>"
							+"<a href='javascript:void(0)' id='close'>X</a>"
						+"</div>"
						+"<div class='checkimg'>"
							+"<img src='security/securityCodeImageAction.action' alt='' id='IMG'/>"
							+"看不清,"
							+"<a href='javascript:void(0)' id='change_img' class='change'>换一个?</a>"
						+"</div>"
						+"<input type='text' name='checknumber' class='checknumber' id='checknumber'/>"
						+"<input type='button' name='' id='send_button' value='立即重发' class='send_button'/>"
					+"</form>"
		);
		$("#confirm_div").append(html);
		$("#close").click(function(){
			$("#resend_checknumber").hide();
		})
		$("#send_button").click(function(){
			var checknumber=$("#checknumber").val();
			/**
			 * 进行ajax提交,检查验证码是否正确
			 */
			 $.ajax({
		            url: "user_checkNumberOk.action",
		            // 数据发送方式
		            type: "post",
		            // 接受数据格式
		            dataType : "json",
		            // 要传递的数据
		          	data :"checknumber="+checknumber,
		            // 回调函数，接受服务器端返回给客户端的值，即result值
		            success : show   
    			 });
			
		})
		
		/**
		 * 点击换一个更新图片
		 */
		$("#change_img").click(function(){
			$("#IMG").attr("src","security/securityCodeImageAction.action?timestamp="+new Date().getTime());
		})
	})
})

function show(result){
    //解析json对象
	$(".right").remove();
	$(".error").remove();
    var json = eval("("+result+")");
    if(json.number=='true'){//验证码正确,则发送邮件,并且关闭当前对话框,显示另外一个对话框
    	//进行ajax提交数据
    	var mail=$("#email").text();
    	var activeCode=$("#activeCode").val();
    	$.ajax({
			 url: "e-mail/mail_resend.action",
		     // 数据发送方式
		     type: "post",
		     // 接受数据格式
		     dataType : "json",
		     // 要传递的数据
		     data :"mail="+mail+"&activeCode="+activeCode
		})
    	$("#resend_checknumber").remove();
    	var html=$("<form action='' id='resend_checknumber' class='resend_checknumber'>"
				+"<div class='checknumber_title'>"
					+"<h3>发送成功</h3>"
					+"<a href='javascript:void(0)' id='close'>X</a>"
				+"</div>"
				+"<span class='success'>"
					+"确认信已经重发到你的邮箱"+mail+"，请查收。"
				+"</span>"
				+"<input type='button' id='make_sure' value='确认'>"
			+"</form>");
    	$("#confirm_div").append(html);
    	$("#close").click(function(){
    		$("#resend_checknumber").remove();
    	})
    	$("#make_sure").click(function(){
			$("#resend_checknumber").remove();
		})
    }else{
    	var info=$("<span class='error'>验证码错误,请重新输入</span>")
    	$("#send_button").before(info);
    }		    
}