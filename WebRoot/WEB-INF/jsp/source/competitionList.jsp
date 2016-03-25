<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>竞赛列表</title>
	<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/source.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/competitionList.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_competitionListPage.js"></script>
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
				<li><a href="competition_list.action"  class="selected">创新竞赛</a></li>
				<li><a href="competition_attendUI.action" >参加竞赛</a></li>
			</ul>
			<%@ include file="/WEB-INF/jsp/public/head.jspf" %>
		</div>
	</div>
	<!-- 头部结束 -->
	
	<!--竞赛列表  -->
	<div class="competition_list_div">
		<span id="userId" value="${user.id }"></span>
		<ul class="competition_list_ul">
			<s:iterator value="recordList">
				<li>
					<div class="competition_list_top">
						<span class="competition_title">${title }</span>
						<a href="competition_detail.action?id=${id }" class="view">浏览详情</a>
						<div class="other_info">
							<table>
								<tr>
									<td>发布人:<a href="">${author }</a></td>
									<td>发起时间:${start }</td>
									<td>截止时间:${end }</td>
								</tr>
								<tr>
									<td>发布单位:计算机学院</td>
									<td>已有作品:<a href="soft_list.action?competitionId=${id }">${count }</a></td>
									<td>浏览次数:158次</td>
								</tr>
							</table>
						</div>
					</div>
	
					<div class="competition_list_middle">
						内容:
						<div class="click_expand">
							<a href="javascript:void(0)">[点击展开]</a>
						</div>
	
						<div class="competition_content">
							<!-- 内容区域 -->
							${description }
							<!-- 内容区域结束 -->
						</div>
	
						<div class="click_close">
							<a href="javascript:void(0)">[收起]</a>
						</div>
	
						<div class="attend_competition">
							<s:form action="competition_attendUI.action" id="form%{id}">
								<s:hidden name="id"></s:hidden>
								<input type="button" class="likebutton2" value="参加竞赛"/>
							</s:form>
						</div>
					</div>
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
						 window.location.href="competition_list.action?pageNum="+pageNum;
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
	<!-- 竞赛列表结束 -->
	<!--竞赛列表  -->
	
	<!--底部版权开始11  -->
	<%@ include file="/WEB-INF/jsp/public/bottom.jspf" %>
	<!--底部版权结束  -->

</body>
</html>