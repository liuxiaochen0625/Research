var isUserLogin="";
var isExists="";
var id;
var userId;
$(function(){
	$(".likebutton2").click(function(){
		id=$(this).siblings('input').val();
		userId=$("#userId").attr("value")
		isUserLogin=isLogin();
		if(isUserLogin=="true"){//判断用户是否登录
			//判断这个用户是否参加了这个比赛
			isExists=isUserExists(id,userId)
			if(isExists=="true"){
				alert("您已经参加这个竞赛了")
			}else{
				$(this).parent('form').submit();
			}
		}else{
			alert('请先登录再参加竞赛')
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

//ajax验证这个用户是否参加了这个竞赛
function isUserExists(id,userId){
	$.ajax({
		url:"competition_existsUser.action",
		type:"post",
		dataType:"json",
		data:"id="+id+"&userId="+userId,
		async:false,
		success:function(result){
			var json=eval("("+result+")");
			isExists=json.exists;
		}
	})
	return isExists;
}