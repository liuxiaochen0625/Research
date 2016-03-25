var isUserLogin="";
$(function(){
	$(".likebutton").click(function(){
		isUserLogin=isLogin();
		if(isUserLogin=="true"){
			$(this).parent('form').submit();
		}else{
			alert('请先登录再下载资源')
		}
	})
	
	$(".likebutton1").click(function(){
		$("#params_form").submit();
	})
})


//ajax函数验证用户是否登录
function isLogin(){
	$.ajax({
		url:"user_isLogin.action",
		type:"post",
		dataType:"json",
		async:false,
		success:function(result){
			var json=eval("("+result+")");
			isUserLogin=json.login;
		}
	})
	return isUserLogin;
}