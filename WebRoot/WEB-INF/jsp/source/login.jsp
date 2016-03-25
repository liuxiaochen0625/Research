<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>研究所资源--登录</title>
	<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/source.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_loginPage.js"> </script>
</head>


<body>

	<!-- 头部 -->
	<div id="header_wrapper">
		<div id="header">
			<span class="logo">科大开源平台</span>
			<ul>
				<li><a href="index.action" >首页</a></li>
				<li><a href="source_list.action" >资源列表</a></li>
				<li><a href="source_uploadUI.action" >我要上传</a></li>
				<li><a href="competition_list.action"  >创新竞赛</a></li>
				<li><a href="competition_attendUI.action" >参加竞赛</a></li>
			</ul>
			<form action="source_search.action" id="form" method="post">
				<input type="text" class="text" value="请输入资源关键字" id="text" name="key"/>
				<span class="search_button"></span>
				<input type="button" class="submit" value="提交" id="tijiao" />
			</form>
			<s:if test="%{#session.user==null}">
				<div class="login_register">
					<a href="user_loginUI.action" class="selected">登录</a>
					<a href="user_addUI.action">注册</a>
				</div>
			</s:if>
			<s:if test="%{#session.user!=null }">
				<div class="person_info">
					<a href="javascript:void(0)" class="xiala">
						<img src="${user.imgpath}" alt="${user.username }"  width="40" height="40" class="member"/>
						<span class="name">
							${user.nickname}
						</span>					
					</a>
					<div class="scrollbar" style="display: none;">
						<ul class="menu">
							<li>
								<a href="user_personalInfo.action">个人中心</a>
							</li>
							<li>
								<a href="">私信</a>
							</li>
							<li>
								<a href="user_updateUI.action">设置</a>
							</li>
							<li>
								<a href="user_logout.action">退出</a>
							</li>
						</ul>
					</div>
				</div>
			</s:if>
		</div>
	</div>
	<!-- 头部结束 -->

	<!-- 登录区域 -->
	<div id="login">
		<s:form action="user_login.action" id="loginform" name="loginform" method="post">
			<b>欢迎登录本网站</b>
			<s:fielderror></s:fielderror>
			<!--此处将打印错误登录信息,由jqeury来动态操控-->
			<!-- <span class="loginError">用户名或者密码错误</span> -->
			<div class="logindiv">
				<label for="username">用户名:</label>
				<input type="text" name="username"  id="username"/>
			</div>
			<div class="logindiv">
				<label for="">密码:</label>
				<input type="password" name="password" value="" id="password"/>
			</div>
			<div class="logindiv">
				 <s:checkbox cssClass="remmber" name="remmber" value="true"></s:checkbox>
				 <p>记住我</p>
			</div>
			<div class="logindiv">
				<input class="submit" type="button" value="登录" id="denglu"/>
			</div>
		</s:form>

		<div class="go_register">
			<span>没有账号?</span>
			<a href="user_addUI.action">马上注册</a>
			<span>忘记密码?</span>
			<a href="user_forget.action">找回密码</a>
		</div>
	</div>
	<!-- 登录区域 -->

	<!--底部版权开始11  -->
	<%@ include file="/WEB-INF/jsp/public/bottom.jspf" %>
	<!--底部版权结束  -->

</body>
</html>