var isUserLogin="";

$(function(){
  	//选择文件js
  	$("#file").change(function(){//判断文件的后缀名是不是.rar,或者.zip
  			$("#maodian4").remove();
			$("#file_info").find('span').text("当前选择的文件:"+$(this).val());
			var filename=$(this).val();
			var lastIndex=filename.lastIndexOf(".")
			var houzhui=filename.substring(lastIndex)
			if(houzhui==".zip"||houzhui==".rar"){
				$(this).siblings(".error").remove();
			}else{
				var html=$("<span class='a2 error' id='maodian4'>请选择zip或者rar</span>")
				$(this).before(html);
			}
  		})
  
	  //验证
	  $("#toUpload").click(function(){
		  	$(".error").remove();
		  	var title=$("#title").val();
			 var description=CKEDITOR.instances.description.getData();
			 var language=$("#language option:selected").text();
			 var html;
			 //标题验证
			 if(title.length<5||title>40){
				 html=$("<span class='error' id='maodian1' style='position:absolute;right:70px;top:2px;'>标题长度介于5-40之间</span>");
				 $(".title_div").append(html);
				 location.hash="maodian1";
			 }else{
				 $(".title_div").find('.error').remove();
			 }
			 
			 //描述验证
			 if(description==""){
				 html=$("<span class='error' id='maodian2' style='font-size:14px;font-family:Microsoft Yahei;'>描述长度不能为空</span>");
				 $(".description_div").before(html);
				 location.hash="maodian2";
			 }else{
				 $(".description_span").next('.error').remove();
			 }
			 
			 //类型验证
			 if(language=="--------"){
				 html=$("<span id='maodian3' class='error a2'>请选择</span>");
				 $("#language").after(html);
				 location.hash="maodian3";
			 }else{
				 $(".type_div_left").find('.error').remove();
				 $(".type_div_right").find('.error').remove();
			 }
			 
			 //文件验证
			var filename=$("#file").val();
			var lastIndex=filename.lastIndexOf(".")
			var houzhui=filename.substring(lastIndex)
			if(houzhui==".zip"||houzhui==".rar"){
				$("#file").siblings(".error").remove();
			}else{
				html=$("<span class='a2 error' id='maodian4'>请选择zip或者rar</span>")
				$("#file").before(html);
				location.hash="maodian4";
			}
			
			//最后检查错误的长度,如果为0就可以提交数据了
			var length=$('.error').length;
			if(length<=0){
				//进行数据提交
				isUserLogin=isLogin();
				if(isUserLogin=="true"){	
					//用ajaxFileUpload传不过去ckeditor里面的内容的值	
					//ajaxFileUpload(title,description,language,type);
					
					//直接提交表单好了
					$("#sourceform").submit();
				}else{
					alert("请先登录再上传资源")
				}
			}
	  })
})


function isLogin(){
	 $.ajax({
         url: "user_isLogin.action",
         // 数据发送方式
         type: "post",
         // 接受数据格式
         dataType : "json",
         async : false,
         // 回调函数，接受服务器端返回给客户端的值，即result值
         success :function(result){
         	var json=eval("("+result+")");
         	isUserLogin=json.login;
         }
	   });
	 return isUserLogin;
}



////这个破东西，问题太多了,直接用表单提交得了，不用ajax上传文件了
///**
// * ajax上传文件函数
// * @param title
// * @param description
// * @param language
// * @param type
// * @returns {Boolean}
// */
//function ajaxFileUpload(title,description,language,type){
//	$("#file")
//    .ajaxStart(function(){
//        var html=$("<div class='gray_bg'><div class='gray_bg_mid'>" +
//        		"<img src='/Research/images/source/loading3.gif' alt='' />上传中......</div></div>");
//        $(".upload_left").append(html);
//        $("body").addClass('loading_bg');
//    })//开始上传文件时显示一个图片
//    .ajaxComplete(function(){
//    	$(".gray_bg").remove();
//    	$("body").removeClass('loading_bg');
//    });//文件上传完成将图片隐藏起来
//	
//	//注意描述必须在url后面，不然会传值不完整，具体原因不清楚，然后由于ajax传值中文乱码，采用网上解决办法,客户端设置两次编码，服务端设置一次转码就能转过来
//	$.ajaxFileUpload
//    (
//        {
//            url:'source_upload.action',
//            secureuri:false,//一般设置为false
//            type: "post",
//            data: {//加入的文本参数  
//                "title": title,  
//                "language":language,
//                "type":type,
//                "description":description
//            },  
//            fileElementId:'file',
//            dataType: 'json',
//            success: function (data,status)  //服务器成功响应处理函数
//            {
//                //alert(data.message);
//                if(typeof(data.error) != 'undefined')
//                {
//                    if(data.error != '')
//                    {
//                        alert(data.error);
//                    }else
//                    {
//                        alert(data.message);
//                    }
//                }
//            },
//            error: function (data, status, e)//服务器响应失败处理函数
//            {
//                alert(e);
//            }
//        }
//    )
//    return false;
//}

