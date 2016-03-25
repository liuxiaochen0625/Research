<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/pageCommon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/competition_create.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".save").click(function(){
				$(".error").remove();
				var content=$("#content").val();
				if(content.length<=0){
						var html=$("<span class='error'>内容不能为空</span>")
						$(".a1").append(html);
				}
				if($(".error").length<=0){
						$("#notice_form").submit();
				}
			});
		})
	</script>	
	<title>发布公告</title>
</head>
<body>
	 <!-- 标题部分 -->
	<div id="title_bar">
		<div id="title_bar_head">
			<div class="title_head"></div>
			<div class="title">
				<img src="${pageContext.request.contextPath}/images/admin/right/title_arrow.gif" border="0" height="13" width="13">
				<span>发布公告</span>
			</div>
			<div class="title_end"></div>
		</div>
	</div>
	<!-- 标题部分结束 -->
	
	<!-- 发布公告 -->
	<s:form name="create" action="notice_%{id==null?'create.action':'edit.action'}" method="post" id="notice_form">
		<s:hidden name="id"></s:hidden>
		<div class="wrapper">
			<div class="create">
				<b>发布公告</b>
				<div class="a1">
					<span class="s1">内容：</span>
					<s:textarea name="content" style="width:500px;height:100px;" id="content"></s:textarea>
				</div>
	
			</div>
			<div class="operator">
				<img class="save" src="${pageContext.request.contextPath}/images/admin/right/save.png" />
				<img class="back" src="${pageContext.request.contextPath}/images/admin/right/goBack.png" onclick="javascript:history.go(-1)"/>
			</div>
		</div>
	</s:form>
	<!-- 发布公告结束 -->
</body>
</html>