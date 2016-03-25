<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/pageCommon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/user_info.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<title>用户信息</title>
</head>
<body>
	<!-- 标题部分 -->
	<div id="title_bar">
		<div id="title_bar_head">
			<div class="title_head"></div>
			<div class="title">
				<img src="${pageContext.request.contextPath}/images/admin/right/title_arrow.gif" border="0" height="13" width="13">
				<span>用户信息</span>
			</div>
			<div class="title_end"></div>
		</div>
	</div>
	<!-- 标题部分结束 -->
	
	<!-- 个人信息开始 -->
	<div class="user_info_div">
		<div class="basic_info">
			<b>基本信息</b>
			<ul class="user_info_ul">
				<li>
					<span>id</span>
					<span>${id }</span>
				</li>

				<li>
					<span>用户名</span>
					<span>${username }</span>
				</li>

				<li>
					<span>昵称</span>
					<span>${nickname }</span>
				</li>

				<li>
					<span>头像</span>
					<span><img src="${imgpath}" alt="" width="40" height="40"/></span>
				</li>
				
				<li>
					<span>注册邮箱</span>
					<span>${mail }</span>
				</li>

				<li>
					<span>分享次数</span>
					<span>${sharecount }次</span>
				</li>

				<li>
					<span>参赛作品数</span>
					<span>5个</span>
				</li>

				<li>
					<span>状态</span>
					<span>
						<s:if test="%{active==1}">
							已激活
						</s:if>
						<s:else>
							未激活
						</s:else>
					</span>
				</li>

				<li>
					<span>加入时间</span>
					<span>${createdate }</span>
				</li>
			</ul>
			
			<div class="description" style="padding-left:5px;">
				简介:
				<div style="width:600px;text-indent:20px;border:1px solid #91C0E3;padding:10px; ">
					${description }
				</div>
			</div>
		</div>

		<div class="source_info">
			<b>分享的资源</b>
			<ul class="source_list_ul">
				<s:iterator value="sourceList">
					<li>
						<a href="source_detail.action?id=${id }">${title }</a>
					</li>
				</s:iterator>
			</ul>
		</div>

		<div class="competition_info">
			<b>参赛作品</b>
			<ul class="soft_list_ul">
				<s:iterator value="softList">
					<li>
						<a href="#">${title }</a>
					</li>
				</s:iterator>
				
			</ul>
		</div>
	</div>
	<!-- 个人信息结束 -->
</body>
</html>