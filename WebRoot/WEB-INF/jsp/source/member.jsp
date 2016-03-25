<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>信息设置</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/source.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_preview.js"></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_memberPage.js"></script>
</head>
<body>

	<!-- 小滚动返回条 -->
	<div class="back" title="点击回到顶部"></div>
	<!-- 小滚动返回条结束 -->

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
			<%@ include file="/WEB-INF/jsp/public/head.jspf" %>
		</div>
	</div>
	<!-- 头部结束 -->

	<div class="member_title">
		<h2>个人信息设置</h2>
	</div>

	<!-- 个人信息区域开始 -->
	<div id="member_info">
		<div class="left">
			<b>设置</b>
			<hr />
			<ul>
				<li class="click" >个人资料</li>
				<li >更改头像</li>
				<li>修改密码</li>
			</ul>
		</div>		
		
		<!--模拟点击加载区域  -->
		<div id="moni_loading" style="display:none">
			<img src="${pageContext.request.contextPath}/images/source/loading.gif"/>loading...
		</div>
		<!--模拟点击加载区域结束  -->
		
		<!--基本信息修改区域  -->
		<div class="right" style="display:block">
			<s:form action="user_update.action" name="updateSimple" type="post" id="updateSimple">
				<!--在这个地方通过jquery动态生成提示信息  -->
				<s:hidden name="flag" value="update_info" id="flag"></s:hidden>
				<s:hidden name="id"  value="%{#session.user.id}"></s:hidden>
				
				<div>
					<b>昵称:</b> 
					<s:textfield cssClass="nickname" name="nickname" value="%{#session.user.nickname==null?#session.user.username:#session.user.nickname}" id="nickname">
					</s:textfield>
				</div>
				<div>
					<b>性别:</b>
					<s:if test="%{#session.user.sex==null}">
						男:<input type="radio" name="sex" value="1" >
						女:<input type="radio" name="sex" value="0">
					</s:if>
					<s:if test="%{#session.user.sex==1}">
						男:<input type="radio" name="sex" value="1" checked>
						女:<input type="radio" name="sex" value="0">
					</s:if>
					<s:if test="%{#session.user.sex==0}">
						男:<input type="radio" name="sex" value="1" >
						女:<input type="radio" name="sex" value="0" checked>
					</s:if>
				</div>
				<div>
					<b class="desc">简介:</b>
					<s:textarea id="person_description" name="description" cols="55" rows="7" style="resize:none;padding-left: 10px;padding-top: 10px;" value="%{#session.user.description}">
					</s:textarea>
				</div>
				<input type="button" value="更新" class="likebutton update" id="update1"/>
			</s:form>
		</div>
		<!--基本信息修改区域结束  -->
		
		<!--头像上传区域  -->
		<div class="right" >
			<div  id="loading" style="display: none">
				<img src="${pageContext.request.contextPath}/images/source/loading.gif">
				<span id="info">正在处理文件,请耐心等候</span>
			</div>
			<s:form action="user_update.action" enctype="multipart/form-data" name="updateImg" type="post" cssClass="upload_img" id="updateImg">
				<s:hidden name="id" value="%{#session.user.id}"></s:hidden>
				<s:hidden name="flag" value="update_img"></s:hidden>
				<div style="width:500px;margin:0px auto;">
					<div id="theimg"><img id="ImgPr" width="100" style="margin-bottom:10px;"/></div>
					<span id="filepath" class="filepath"></span>
					<input type="file" id="file0" class="file0" name="upload"/>
					<a id="choose">选择图片</a>
				</div>
				<input type="button" class="likebutton forimg" value="更新" id="update2"/>
				<!--这里模拟一下进度条 ,只是简单模拟了,为了界面好看用的,并没有通过后台的精确计算进度条 -->
				<div class="progress" style="display:none" id="progress">
					<span class="bar_span" id="bar_span" ></span>
				</div>
			</s:form>
			<span class="notice">注意:请将上传的头像大小限制在100k以内,否则会上传失败</span>
		</div>
		<!--头像上传区域结束  -->
		
		<!-- 密码修改区域开始-->
		<div class="right">
			<s:form action="user_update.action" name="updatePassword" id="updatePassword" type="post">
				<!--传隐藏值过去  -->
				<s:hidden name="id" value="%{#session.user.id}"></s:hidden>
				<s:hidden name="flag" value="update_password"></s:hidden>
				<div>
					<label for="password">旧密码:</label>
					<input type="password" name="password" class="a5" id="oldpassword"/>
				</div>
				<div>
					<label for="newpassword">新密码:</label>
					<input type="password" name="newpassword" class="a5" id="newpassword"/>
				</div>
				<div>
					<label for="newpassword1">确认新密码:</label>
					<input type="password" name="newpassword1" class="a5" id="newpassword1"/>
				</div>
				<div>
					<input type="button" value="更新" class="likebutton forpassword" id="update3"/>
				</div>
				<br/>
			</s:form>
		</div>
		<!-- 密码修改区域结束 -->
	</div>
	<!-- 个人信息区域结束 -->
	
	<!--底部版权开始11  -->
	<%@ include file="/WEB-INF/jsp/public/bottom.jspf" %>
	<!--底部版权结束  -->
</body>
</html>