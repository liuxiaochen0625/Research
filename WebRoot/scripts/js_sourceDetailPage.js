/**
 * 详细资源页面js
 */
var isUserLogin="";
$(function(){
	$("#download").click(function(){
		//先验证是否登录了,ajax验证
		isUserLogin=isLogin();
		if(isUserLogin=="true"){
			$("#downloadform").submit();
		}else{
			alert("请先登录再下载资源")
		}
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