<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>资源列表</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/source.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sourceList.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_sourceListPage.js"></script>
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
				<li><a href="source_list.action" class="selected">资源列表</a></li>
				<li><a href="source_uploadUI.action" >我要上传</a></li>
				<li><a href="competition_list.action"  >创新竞赛</a></li>
				<li><a href="competition_attendUI.action" >参加竞赛</a></li>
			</ul>
			<%@ include file="/WEB-INF/jsp/public/head.jspf" %>
		</div>
	</div>
	<!-- 头部结束 -->
	
	<!-- 排序条件 -->
	<s:form action="source_list.action" id="params_form"> 
		<div class="sort_case">
			<div class="sort_div">
				<span>查找条件:</span>
				<span>语言:</span>
				<s:select list="#languageList" headerKey="all" headerValue="全部" name="param1" listKey="language" listValue="language"/>
	
				<span>类型:</span>
				<s:select list="#labelList" headerKey="all" headerValue="全部" name="param2" listKey="type" listValue="type" />
				
				<span>下载次数:</span>
				<s:select name="param3" id="param3" list="#param3List" headerKey="不参与排序" headerValue="不参与排序" listKey="key" listValue="value"/>
	
				<span>日期:</span>
				<s:select name="param4" id="param4" list="#param4List" headerKey="不参与排序" headerValue="不参与排序" listKey="key" listValue="value"/>
	
				<input type="button" class="likebutton1" value="确定" />
			</div>
		</div>
	</s:form>
	<!-- 排序条件样式 -->
	

	<!--资源列表开始，1页显示12条数据  -->
	<div class="source_list_div">
		<ul class="source_list_ul">
			<s:iterator value="recordList">
				<li >
					<!-- 顶部-->
					<div class="source_list_top">
						<span class="source_title">${title }</span>
						<a href="source_detail.action?id=${id}" class="view">浏览详情</a>
						<div class="upload_name">
							<table>
								<tr>
									<td>上传者:<a href="">${user.nickname }</a></td>
									<td>上传于:${time}</td>
									<td><b>${count }</b>次下载</td>
								</tr>
								<tr>
									<td>语言:${language }</td>
									<td>标签:${type }</td>
								</tr>
							</table>
						</div>
					</div>
					<!-- 顶部结束 -->
	
					<!-- 中部内容区域 -->
					<div class="source_list_middle">
						内容:
						<div class="click_expand">
							<a href="javascript:void(0)">[点击展开]</a>
						</div>
						<div class="source_content">
							<!-- 内容区域 -->
							${description }							
							<!-- 内容区域结束 -->
						</div>
						<!-- 点击收起 -->
						<div class="click_close">
							<a href="javascript:void(0)">[收起]</a>
						</div>
						<!-- 点击收起 -->
						<div class="download_bottom">
							<s:form action="source_download.action" method="post" id="form%{id}">
								<s:hidden name="id"></s:hidden>
								<input type="button" value="下载" class="likebutton" />
							</s:form>
						</div>
					</div>
					<!-- 中部内容区域 -->
				</li>
			</s:iterator>
			
			<div class="jogger">
				<a href="javascript:gotoPage(1)" title="首页">&lt;</a>
				<s:iterator begin="%{beginPageIndex}" end="%{endPageIndex}" var="num">
					<s:if test="#num==currentPage">
						<span class="current">${num}</span>
					</s:if>
					<s:else>
						<a href="javascript:gotoPage(${num })">${num}</a>
					</s:else>
				</s:iterator>
				<a href="javascript:gotoPage(${pageCount })" title="尾页">&gt;</a>
				<select class="xiala" id="xiala" onchange="gotoPage(this.value)">
					<s:iterator begin="1" end="pageCount" var="num">
						<option value="${num }">第${num }页</option>
					</s:iterator>
				</select>
				
				<script type="text/javascript">
					function gotoPage(pageNum){
						/* window.location.href="source_list.action?pageNum="+pageNum; */
						$("#params_form").append("<input type='hidden' name='pageNum' value='"+pageNum+"'>");
						$("#params_form").submit();
					}
				</script>
				<script type="text/javascript">
					$("#xiala").val("${currentPage}")
				</script>
			</div>
			<div class="jogger">
				<span class="page_info">页次:${currentPage}/${pageCount}</span>
				<span class="page_info">每页显示:${pageSize}</span>
				<span class="page_info">查询到:${recordCount}个</span>
			</div>
		</ul>
	</div>
	<!--资源列表结束 -->
	
	<!--底部版权开始11  -->
	<%@ include file="/WEB-INF/jsp/public/bottom.jspf" %>
	<!--底部版权结束  -->
</body>
</html>
