<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>研究所资源--注册</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/source.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_registerPage.js"></script>
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
				<li><a href="competition_list.action" >创新竞赛</a></li>
				<li><a href="competition_attendUI.action" >参加竞赛</a></li>
			</ul>
			<form action="source_search.action" id="form" method="post">
				<input type="text" class="text" value="请输入资源关键字" id="text" name="key"/>
				<span class="search_button"></span>
				<input type="button" class="submit" value="提交" id="tijiao" />
			</form>
			<s:if test="%{#session.user==null}">
				<div class="login_register">
					<a href="user_loginUI.action" >登录</a>
					<a href="user_addUI.action" class="selected">注册</a>
				</div>
			</s:if>
			<s:if test="%{#session.user!=null }">
				<div class="person_info">
					<a href="javascript:void(0)" class="xiala">
						<img src="${user.imgpath}" alt="${user.username }" class="member" width="40" height="40"/>
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

	
	<!-- 注册区域 -->
	<div id="register">
		<b>欢迎加入本网站</b>
		<s:form action="user_add.action" id="registerform" name="register" method="post">
			<s:hidden name="active" value="0"></s:hidden>
			<div id="result">
			</div>
			<div class="registerdiv">
				<label for="username">用户名:</label>
				<s:textfield name="username" id="username1" cssClass="a1"></s:textfield>
				<strong style="color:red">*</strong>
			</div>
			
			<div class="registerdiv">
				<label for="password">密码:</label>
				<s:password name="password" id="password1" cssClass="a1"></s:password>
				<strong style="color:red">*</strong>
			</div>
			
			<div class="registerdiv">
				<label for="">确认密码:</label>
				<s:password name="repassword" id="repassword" cssClass="a1"></s:password>
				<strong style="color:red">*</strong>
			</div>
			
			<div class="registerdiv">
				<label>注册邮箱:</label>
				<s:textfield name="mail" id="mail" cssClass="a1"></s:textfield>
				<strong style="color:red">*</strong>
			</div>
				
			<div class="registerdiv">
				<label>验证码:</label>
				<input type="text" name="checknumber" id="checknumber" class="check">
				<a href="javascript:void(0)">
					<img src="security/securityCodeImageAction.action" alt="" title="点击更换图片"  id="Verify">
				</a>
			</div>
			
			
			<div class="registerdiv">
				<input class="register_submit" type="button" value="注册" id="go_register"/>
			</div>
			
			<div class="registerdiv">
				<span>已经注册,</span><a href="user_loginUI.action" class="go_login">马上登陆</a>
			</div>
			
			<!--防止重复提交令牌环  -->
			<s:token></s:token>
			<!--防止重复提交令牌环  -->
		</s:form>
	</div>
	<!-- 注册区域 -->

	<!--底部版权开始11  -->
	<%@ include file="/WEB-INF/jsp/public/bottom.jspf" %>
	<!--底部版权结束  -->

</body>
</html>