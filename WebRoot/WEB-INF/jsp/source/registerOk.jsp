<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<title>注册成功</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registerOk.css" />
</head>
<body>
	<div class="register_Ok">
		<div class="register_title">
			<span>
				注册/成功
			</span>
		</div>
		<div class="register_content">
			<img src="./images/succ.jpg" alt="" />
			<span>恭喜，您已成功注册本网站账号！</span>
			<span>
				<b id="register_ok_time" style="color:red">3</b>秒后返回后首页
			</span>
		</div>
	</div>
	<script type="text/javascript">
		//3秒后返回到主页
		var time=document.getElementById("register_ok_time").innerHTML;
		setInterval(function(){
			time--;
			if(time<=0){
				window.location.href="http://localhost:8080/Research/index.action";
			}
			document.getElementById("register_ok_time").innerHTML=time;
		},1000)
	</script>
</body>
</html>