<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/pageCommon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/source_detail.css">
	<title>单个资源页面</title>
</head>
<body>
	<!-- 标题部分 -->
	<div id="title_bar">
		<div id="title_bar_head">
			<div class="title_head"></div>
			<div class="title">
				<img src="${pageContext.request.contextPath}/images/admin/right/title_arrow.gif" border="0" height="13" width="13">
				<span>单个资源详情</span>
			</div>
			<div class="title_end"></div>
		</div>
	</div>
	<!-- 标题部分结束 -->
	
	<div class="source_detail_div">
		<div class="detail_div">
			<b>资源详情</b>
			<table class="source_detail_table" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td>Id</td>
					<td>${source.id}</td>
				</tr>
				<tr>
					<td>标题</td>
					<td>${source.title }</td>
				</tr>
				<tr>
					<td>作者</td>
					<td>${source.user.nickname }</td>
				</tr>
				<tr>
					<td>下载次数</td>
					<td>${source.count }次</td>
				</tr>
				<tr>
					<td>语言</td>
					<td>${source.language }</td>
				</tr>
				<tr>
					<td>类型</td>
					<td>${source.type }</td>
				</tr>
				<tr>
					<td>状态</td>
					<td>${source.status }</td>
				</tr>
				<tr>
					<td>上传时间</td>
					<td>${source.time }</td>
				</tr>
				<tr>
					<td>操作</td>
					<td><a href="${pageContext.request.contextPath}/source_download.action?id=${source.id}">下载</a></td>
				</tr>
			</table>
			<div class="description">
				<span>简介:</span>
				<div class="content">
				${source.description }
				</div>
			</div>
		</div>
	</div>	
</body>
</html>