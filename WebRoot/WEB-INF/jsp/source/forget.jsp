<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>找回密码</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/source.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/forget.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_forgetPage.js"> </script>
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
			<%@ include file="/WEB-INF/jsp/public/head.jspf" %>
		</div>
	</div>
	<!-- 头部结束 -->

	<!-- 找回密码区域 -->
	<div class="forget">
		<b>找回密码</b>
		<em class="help_em">(通过填写注册的邮箱,发送一封邮件,然后找回自己的密码)</em>
		<hr />
		<form action="" class="forget_form">
			<label for="mail" class="label_mail">请填写注册的邮箱:</label>
			<input type="text" name="mail" class="mail"/>
			<input type="button" value="发送" class="send" id="send">
		</form>
	</div>
	<!-- 找回密码区域结束 -->

	<!--底部版权开始11  -->
	<%@ include file="/WEB-INF/jsp/public/bottom.jspf" %>
	<!--底部版权结束  -->
</body>
</html>