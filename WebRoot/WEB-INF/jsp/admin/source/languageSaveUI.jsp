<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/pageCommon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/admin/languageSave.js"></script>
	<title>语言编辑页面</title>
</head>
<body>
	<!-- 标题部分 -->
	<div id="title_bar">
		<div id="title_bar_head">
			<div class="title_head"></div>
			<div class="title">
				<img src="${pageContext.request.contextPath}/images/admin/right/title_arrow.gif" border="0" height="13" width="13">
				<span>语言编辑</span>
			</div>
			<div class="title_end"></div>
		</div>
	</div>
	<!-- 标题部分结束 -->
	
	<!-- 语言选项保存 -->
	<div class="wrapper" style="padding-left:150px;padding-top:10px;">	
		<s:form action="language_%{id==null?'add.action':'edit.action'}" id="language_form">	
			<s:hidden name="id" id="id"></s:hidden>		
			<div class="source_label_saveUI" style="background:#fff;width:600px;padding:10px;">
				<label for="">输入语言：</label>
				<s:textfield name="language" cssClass="language"></s:textfield>
			</div>
		</s:form>
		<div style="width:600px;text-align:center;padding-top:10px;">
			<img class="save" src="${pageContext.request.contextPath}/images/admin/right/save.png" style="cursor: pointer;"/>
			<img class="back" src="${pageContext.request.contextPath}/images/admin/right/goBack.png" onclick="javascript:history.go(-1)" style="cursor: pointer;"/>
		</div>
	</div>
	<!-- 语言选项保存结束 -->
</body>
</html>