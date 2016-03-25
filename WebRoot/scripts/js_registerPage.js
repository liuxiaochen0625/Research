//有一部分验证定义在js.js文件里面	
$(function(){
	  // 验证用户名和密码还有重复密码的js
		  $("#register div .a1").blur(function(){ 
		      $(this).parent().find('.a2').remove();
		      if($(this).is("#password1")){
		          if(this.value==""){
		              var info=$('<span class="a2 error">密码不得为空</span>')
		              $(this).parent().append(info);
		          }else{
		        	  if($(this).val().length<4){
		        		  var info=$('<span class="a2 error">密码长度不能小于4</span>')
		                  $(this).parent().append(info);
		        	  }else{
		        		  if($(this).val().length>15){
		        			  var info=$('<span class="a2 error">密码长度不能大于15</span>')
		                      $(this).parent().append(info);
		        		  }else{
		        			   var info=$("<span class='a2 righta'>正确</span>");
		        	           $(this).parent().append(info);
		        		  }
		        	  }
		          }
		      }
		      if($(this).is("#repassword")){
		          if(this.value==""||this.value!=$("#password1").val()){
		              var info=$('<span class="a2 error">两次密码不一致</span>')
		              $(this).parent().append(info);
		          }else{
		        	  if($(this).val().length>15){
		    			  var info=$('<span class="a2 error">密码长度不能大于15</span>')
		                  $(this).parent().append(info);
		    		  }else{
		    			   var info=$("<span class='a2 righta'>正确</span>");
		    	           $(this).parent().append(info);
		    		  }
		          }
		       }
		  })
		
		  /**
		   * 验证码验证js
		   */
		  $("#checknumber").blur(function(){
		    $(this).parent().find('.checknumberinfo').remove();
		    if($(this).val()==""){
		          var info=$('<div class="checknumberinfo"><span class="a2 error">请输入验证码</span></div>')
		          $(this).parent().append(info);
		    }else{
		      $(this).parent().find('.checknumberinfo').remove();
		    }
		  })
		      
		  /**
		   * 验证邮箱是否正确的js
		   */
		   $("#mail").blur(function(){
			   
			   if($(this).val()==""){
				   var info=$("<span class='a2 error'>请填写邮箱</span>");
		    	   $(this).parent().append(info);
			   }else{
				   var reg= /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
				   if(reg.test($(this).val())){//验证邮箱的形式是否合法
					   //合法之后,还要验证邮箱是否注册了,采用ajax方式异步验证邮箱是否注册
					   var params=$("#mail").serialize();
//					   alert(params)
					   $.ajax({
				            url: "e-mail/mail_exits.action",
				            // 数据发送方式
				            type: "post",
				            // 接受数据格式
				            dataType : "json",
				            // 要传递的数据
				          	data : params,
				            // 回调函数，接受服务器端返回给客户端的值，即result值
				            success :function(result){
				            	var json=eval("("+result+")");
				            	if(json.exits=='no'){
				            		var info=$("<span class='a2 righta'>邮箱可以注册</span>")
				            		$("#mail").parent().append(info);
				            	}else{
				            		var info=$("<span class='a2 error'>邮箱已经被占用</span>")
				            		$("#mail").parent().append(info);
				            	}
				            }
					   });
				   }else{//显示邮箱格式不对
					   var info=$("<span class='a2 error'>邮箱格式不正确</span>");
			    	   $(this).parent().append(info);
				   }
			   }
		   })
		  
			/**
			*验证用户名是否存在
			*/
			$("#username1").blur(function(){
			 	// 序列化表单的值
				var params=$(this).serialize();
				//alert(params)
				
				var username=$("#username1").val();
				if(username.indexOf(" ")>=0){//含有空格
						var info=$('<span class="a2 error">用户名不能含有空格</span>');
						$(".registerdiv:first").append(info);
				}else{//不含有空格
					if($(this).val().length<6){
						var info=$('<span class="a2 error">用户名长度不能小于6</span>');
						$(".registerdiv:first").append(info);
					}else{
						if($(this).val().length>13){
							var info=$('<span class="a2 error">用户名长度不能大于13</span>');
							$(".registerdiv:first").append(info);
						}else{
							 $.ajax({
					            url: "user_exits.action",
					            // 数据发送方式
					            type: "post",
					            // 接受数据格式
					            dataType : "json",
					            // 要传递的数据
					          	data : params,
					            // 回调函数，接受服务器端返回给客户端的值，即result值
					            success : show   
			       			 });
			       		}
	       			 }
	       		}
			})
			
			
			/**
			*验证码刷新函数
			*/
			//点击图片更换验证码
			$("#Verify").click(function(){
				$(this).attr("src","security/securityCodeImageAction.action?timestamp="+new Date().getTime());
			})

		  
		   //如果没有填完注册信息或者有错误就不能提交
		  $("#go_register").click(function(){	
			var firstInfo=$("#RIGHT").text();
			$('#password1').trigger('blur');
			$('#repassword').trigger('blur');
			$('#mail').trigger('blur');
		    $('.check').trigger('blur');
			var errLength=$('.error').length;//检查出现错误的长度,当然是jquery去发现有多少个.error的css类
		   //不为空的话说明有错误，那么就执行不成功
		//		alert(firstInfo);
		//	    alert(errLength)
			
			//点击提交时，用ajax方式验证验证码是否正确，正确则提交，错误的话则显示验证码输入错误
			$.ajax({
				url:"user_checkNumberOk.action",
				type:"post",
				data:"checknumber="+$("#checknumber").val(),
				dataType:"json",
				success:function(msg){
		//				alert(msg)
					var json = eval("("+msg+")");
					if(json.number=='true'){
						if(errLength==0&&firstInfo=="用户名可用"){
		//				    	alert('注册成功')
					    	$("#registerform").submit();
					    }else{
		//				    	alert('注册失败')
					    	$('#username1').trigger('blur');
					    }
					}else{				
						$('#username1').trigger('blur');
						 var info=$('<div class="checknumberinfo"><span class="a2 error">验证码不正确</span></div>')
						 $("#checknumber").parent().find('.checknumberinfo').remove();
				          $("#checknumber").parent().append(info);
					}
				}
			})	
		  })
})
		
//回调函数
function show(result){
    //测试result是否从服务器端返回给客户端
    //alert(result);
    
    //解析json对象
    var json = eval("("+result+")");
    //var obj = "用户是是否存在: "+json.exits;
    //alert(json.exits)
    if(json.exits=='true'){
    	var info=$('<span class="a2 error">用户名已经存在</span>')
    }else{
    	var info=$('<span class="a2 righta" id="RIGHT">用户名可用</span>')
    }
    $(".registerdiv:first").append(info);		    
}