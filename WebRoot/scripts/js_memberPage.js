	$(function(){
//=====================================================基本信息修改js============================================================
			//1.点击触发验证函数,如果通过就进行ajax提交
			$("#update1").bind('click',function(){
				if(confirm("确定要更新吗？")){
					$("#nickname").trigger('blur');//调用nickname的blur函数
					$("#person_description").trigger('blur');//调用它的blur函数
					//如果都验证通过,那么就可以提交数据
					 if($("#updateSimple .error").length==0){
						//序列化传过来的参数
						var params=$("#updateSimple").serialize();
						//alert(params);
						//进行ajax提交
						$.ajax({
							url: "user_update.action",
				            // 数据发送方式
				            type: "post",
				            // 接受数据格式
				            dataType :"json",
				            // 要传递的数据
				          	data : params,
				            // 回调函数，接受服务器端返回给客户端的值，即result值
				            success : check   
						}) 
					}	 
				}
			})
			//验证昵称长度是否小于等于10,可以为空
			$("#nickname").blur(function(){
				//alert($(this).val().length)
				//首先去除掉之前的错误信息
				$(this).next().remove();
				var nickname=$(this).val();
				if(nickname.indexOf(" ")>=0){//含有空格键则验证失败
					var info=$("<span class='a2 error'>昵称不能含有空格</span>")
						$(this).parent().append(info)
				}else{
					if($(this).val().length>10){
						var info=$("<span class='a2 error'>昵称长度不能大于10</span>")
						$(this).parent().append(info)
					}else{
						if($(this).val().length<=0){
							var info=$("<span class='a2 error'>昵称不能为空</span>")
							$(this).parent().append(info)
						}
					}
				}
			})	
			//验证简介的长度是否大于150个字
			$("#person_description").blur(function(){
				$(this).next().remove();
				if($(this).val().length>150){
					var info=$("<span class='a2 error'>简介不能超过150字</span>")
					$(this).parent().append(info)
				}
			})
			
			
//===============================================基本信息修改结束js=========================================================================
	

			/**
			*显示图像路径
			**/
			$("#file0").change(function(){
				$("#filepath").text("当前选择的文件:"+$(this).val())
			})	
			
			
			/**
			 *2.上传图片的js,决定采用ajax方式上传图片,由于之前jquery的$.ajax方法无法获取文件enctype="multipart/form-data"的
			 *表单数据,这里只能采用jquery.form.js插件来实现ajax上传图片的方式了
			 */
			//点击按钮触发函数
			/**
			 * version1,点击直接上传图片,不采用ajax方式
			 */
	/*		$("#update2").click(function(){
				//alert("点击了图片更新按钮")
				//theError是在js_preview中定义的,如果用户选错了文件,就会出现错误提示
				if($("#theError").length>0){//文件类型不正确
					alert("选择的文件必须是图片")
				}else{//文件类型正确
					//选取的文件不能为空
					if($("#file0").val()){
						$("#updateImg").submit();
					}else{
						//如果用户没有选文件，就让他选文件
						var info=$("<span class='a2 error' id='theError'>请选择一张图片</span>");
						$("#theimg").before(info);//在id为theimg的标签前面插入info
					}
				}
			})*/
			
			/**
			 * version2,使用jquery的ajaxfileupload.js上传文件
			 */
			$("#update2").click(function(){
				//alert("点击了图片更新按钮")
				//theError是在js_preview中定义的,如果用户选错了文件,就会出现错误提示
				if($("#theError").length>0){//文件类型不正确
					alert("选择的文件必须是图片")
				}else{//文件类型正确
					//选取的文件不能为空
					if($("#file0").val()){//这里采用ajax的方式来上传图片啦	
						$("#loading")
				        .ajaxStart(function(){
				        	$(this).show();
				            setTimeout(function(){//延时几秒,如果超时,说明上传失败,因为文件过大
				            	$("#progress").show();
								doProgress(50);
				            },5000)
				        })
				        .ajaxComplete(function(){
				        	$("#progress").show();
				        	doProgress(100);
				        });
						
						//获取参数
						var params=$("#updateImg").serialize();
//						alert(params);
						 $.ajaxFileUpload
					        (
					            {
					                url:'user_update.action?'+params,//用于文件上传的服务器端请求地址,加上表单域里的隐藏参数
					                secureuri:false,//一般设置为false
					                fileElementId:'file0',//文件上传空间的id属性  <input type="file" id="file0" name="upload" />
					                dataType: 'json',//返回值类型 一般设置为json
					                success:check1,
					            }
					        )
					}else{
						//如果用户没有选文件，就让他选文件
						var info=$("<span class='a2 error' id='theError'>请选择一张图片</span>");
						$("#theimg").before(info);//在id为theimg的标签前面插入info
					}
				}
			})
			
			
//=================================================密码修改js开始=============================================================
			/**
			 * 这一部分为验证密码修改,以及验证通过之后进行ajax提交的代码
			 */
			$("#update3").click(function(){
//				alert("点击了更新密码按钮")
				//依次验证原始密码,新密码,新密码确认是否合法,为了方便起见，将文本框加上一个样式a5，为了编码方便
				$(".a5").trigger('blur');//这个函数就是让三个文本框的blur函数都执行一次
				//验证之后判断是否还有错误,有则不能提交,否则就进行ajax提交,进行ajax提交还会返回旧密码是否正确的信息,如果正确,后台会更新密码,如果不正确,前台根据后台返回的信息进行显示
				if($("#updatePassword .error").length==0){
					//序列化表单的值
					var params=$("#updatePassword").serialize();
//					alert(params)
					//进行ajax提交
					$.ajax({
						url: "user_update.action",  //url地址
			            // 数据发送方式
			            type: "post",
			            // 接受数据格式
			            dataType :"json",
			            // 要传递的数据
			          	data : params,
			            // 回调函数，接受服务器端返回给客户端的值，即updateInfo值
			            success :  check2 
					})
				}else{
	
				}
				
				
			})
			//定义每个文本框的blur函数
			$("#oldpassword").blur(function(){
				//旧密码要输入才能进行下一步,否则报错
				$(this).parent().find('.a2').remove();
				if($(this).val().length<=0){
					var info=$("<span class='a2 error'>请输入旧密码</span>")
					$(this).parent('div').append(info);
				}
			})
			$("#newpassword").blur(function(){
				$(this).parent().find('.a2').remove();
				//新密码要大于4位并且小于16位
				if($(this).val()==""){//没有输入新密码
					var info=$("<span class='a2 error'>请输入新密码</span>")
					$(this).parent('div').append(info);
				}else{
					if($(this).val().length<4){
						var info=$("<span class='a2 error'>新密码要长度要大于4</span>")
						$(this).parent('div').append(info);
					}else{
						if($(this).val().length>15){
							var info=$("<span class='a2 error'>密码长度不能超过15</span>")
							$(this).parent('div').append(info);
						}else{
							var info=$("<span class='a2 righta'>正确</span>")
							$(this).parent('div').append(info);
						}
					}
				}
				
			})
			$("#newpassword1").blur(function(){
				$(this).parent().find('.a2').remove();
				//两次密码要一致
				if($(this).val()==""){//没有输入确认密码
					var info=$("<span class='a2 error'>请输入确认密码</span>")
					$(this).parent('div').append(info);
				}else{
					if($(this).val()!=$("#newpassword").val()){
						var info=$("<span class='a2 error'>两次密码不一致</span>")
						$(this).parent('div').append(info);
					}else{
						var info=$("<span class='a2 righta'>正确</span>")
						$(this).parent('div').append(info);
					}
				}
				
			})
			
			
//==================================================密码修改js结束==============================================================
			
			
	})
		
	//回调 函数,更新个人资料的基本信息
	function check(updateInfo){
		//解析json数据
		var json=eval("("+updateInfo+")");
		//alert(json.status);
		if(json.updateInfo=="failure"){//更新失败
			$(".updateError").remove();
			$(".righta").remove();
			var info=$("<span class='updateError'>昵称已经存在</span>")
			$("#flag").before(info);
		}else{//更新成功
			var i=5;
			//设置3秒钟刷新界面,这里实在想不出来怎么设置动态的倒计时
			$(".updateError").remove();
			$(".righta").remove();
			var info=$("<span class='a2 righta'>更新成功,<b id='time'>"+i+"</b>秒后自动刷新</span>")
			$("#flag").before(info);	
			var time=document.getElementById("time").innerHTML-1;
		    //启用一个定时器
		    window.setInterval(function(){
		    	document.getElementById("time").innerHTML=time;
		    	time--;
		    	if(time<=0){
		    		window.location.reload();
		    	}
		    },1000);
		}
	}
	
	
	var flag;
	//更新图像成功的回调函数
	/**
	 * 其实更新图像回调函数可以不用写了，基本没怎么用到，而是在ajaxComplete里面，只要上传图片成功，那么就会调用模拟的画进度条函数，上传失败的话，延时5秒钟，然后提示上传失败
	 */
	function check1(updateInfo){
		var json=eval("("+updateInfo+")");
		flag=json;//给全局变量设值
//		alert(json.updateInfo);
/*		if(json.updateInfo=='success'){//表示更新成功
			*//**
			 * 模拟进度条的显示,让member.jsp页面的进度条显示出来
			 *//*
			//1.首先得让隐藏的div显示出来
			$("#progress").show();
			//2.调用进度条函数,参数表示显示多少。100表示显示100%,第二个表示上传的快慢的参数
			doProgress(100);
		}*/
	}

	/**
	 * 修改密码的回调函数
	 */
	function check2(updateInfo){
		var json=eval("("+updateInfo+")");
		if(json.updateInfo=='success'){//更新成功,显示更新成功信息，并且五秒之后刷新界面
			var i=5;
			//首先移除上一次的正确信息，避免多次刷新重复出现
			$("#updatePassWordSuccess").remove();
			var info=$("<div id='updatePassWordSuccess'><span class='a2 righta'>更新成功,<b id='time1'>5</b>秒之后刷新界面</span></div>")
			$("#updatePassword").append(info);
			//计时开始
			var time=document.getElementById("time1").innerHTML-1;
			window.setInterval(function(){
		    	document.getElementById("time1").innerHTML=time;
		    	time--;
		    	if(time<=0){
		    		window.location.reload();
		    	}
		    },1000);
		}else{//更新失败,显示旧密码不正确的信息
			//首先移除上一次产生的错误信息,如果没有也不会报错
			$("#oldpassword").parent().find('error').remove();
			var info=$("<span class='a2 error'>旧密码不正确</span>");
			$("#oldpassword").parent().append(info);
		}
	}
	
	//进度条显示函数
	var time=0;
	function doProgress(percent){
		if(time<=percent){
			//产生随机数,如果是平稳增长，感觉达不到模拟效果。
			setTimeout("doProgress("+percent+")",Math.floor(Math.random()*50+1));
			setProgress(time);
			time++;
		}else{//模拟完成之后就刷新界面了
			//如果不是上传100%进度条，说明上传文件超过限制,则要停止3秒在刷新
			if(percent<100){
				alert('文件超过了100k,请重新上传')
			}
			if(percent==100){
				alert('头像更新成功')
			}
			window.location.reload();
		}
	}
	
	function setProgress(time){
		if(time){
			$("#bar_span").css("width",String(time)+"%");
			/*$("b").text(ress+"%");//使用这个不兼容IE*/
			document.getElementById("bar_span").innerHTML=time+"%";
		}
	}