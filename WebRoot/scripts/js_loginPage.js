		/**
		*登录的js
		*/
var time=0;
$(function(){

			$("#denglu").click(function(){
				$("#username").trigger('blur');
				$("#password").trigger('blur');
				if($(".error").length==0){
					time++;
					if(time>=3){
						$(".loginError").remove();
						var info=$("<span class='loginError'>3次登录失败,请点击<a class='forget' href='user_forget.action'>忘记密码？</a>找回</span>");
						$("#loginform").find("b").append(info);
					}
					//序列化传过来的参数
					var params=$("#username").serialize()+"&"+$("#password").serialize();
					//alert(params);
					//进行ajax验证
					$.ajax({
				            url: "user_loginStatus.action",
				            // 数据发送方式
				            type: "post",
				            // 接受数据格式
				            dataType : "json",
				            // 要传递的数据
				          	data : params,
				            // 回调函数，接受服务器端返回给客户端的值，即result值
				            success : check   
		       		}); 
				}
			})
		
			$("#username").blur(function(){
				/* if($(this).val().length==0){
					alert('输入用户名')
				} */
				$(this).parent().find(".a2").remove();
				if($(this).val().length==0){
					var info=$("<span class='a2 error'>请输入用户名</span>")
					$(this).parent().append(info)
				}
				
			})
			$("#password").blur(function(){
				/* if($(this).val().length==0){
					alert('输入密码')
				}*/
				$(this).parent().find(".a2").remove();
				if($(this).val().length==0){
					var info=$("<span class='a2 error'>请输入密码</span>")
					$(this).parent().append(info)
				}
			})
			
			
			//显示用户下拉框的js
			$(".person_info").hover(function(){
			    $(".scrollbar").show();
			},function(){
			    $(".scrollbar").hide();
			})
			
		})
		/**
		*回调函数,回调函数的参数接受的是strtus2配置文件中传过来的json类型的loginInfo数据
		*/
		function check(loginInfo){
			//解析json数据
			var json=eval("("+loginInfo+")");
//			alert(json.status);
			if(json.status=="failure"){//如果登录失败的话就打印错误信息到登陆界面
			//避免用户一直点登录，所以要先去掉错误信息，如果为找到错误信息，js并不会出错，
				if(time<3){
					$(".loginError").remove();
					var info=$("<span class='loginError'>用户名或者密码错误</span>")
					$("#loginform").find("b").append(info);
				}
			}else{//登录成功，则提交表单
				$(".loginError").remove();
				var mail=json.mail;
				if(json.active=="0"){
					var info=$("<span class='loginError'>用户未激活,请到"+mail+"邮箱激活用户</span>")
					$("#loginform").find("b").append(info);
				}else{
					$("#loginform").submit();
				}
			}
}
			