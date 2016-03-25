<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>竞赛详情</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/source.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/softList.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/competitionDetail.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/js_competitionDetailPage.js"></script>
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
				<li><a href="competition_list.action" class="selected" >创新竞赛</a></li>
				<li><a href="competition_attendUI.action" >参加竞赛</a></li>
			</ul>
			<%@ include file="/WEB-INF/jsp/public/head.jspf" %>
		</div>
	</div>
	<!-- 头部结束 -->
	
	<!--竞赛详情开始  -->
	<div class="wrapper_competitionDetail">
		<h2>${title }</h2>
		<div class="detail_div">
			<div class="top_title">
				<span>${title }</span>
			</div>
			<table>
				<tr>
					<td>发起人:<a href="javascript:void(0)">${author }</a></td>
					<td>发起时间:${start }</td>
					<td>截止时间:${end }</td>
				</tr>
				<tr>
					<td>状态:${status }
					</td>
					<td>已有作品:<a href="#">${count }个</a></td>
					<td>
						<form action="competition_attendUI.action" method="post">
							<input type="button" class="likebutton2" id="attend_button" value="参加竞赛" />
							<s:hidden name="id" ></s:hidden>
							<span id="userId" value="${user.id }"></span>
						</form>
						
					</td>
				</tr>
			</table>
			
			

			<div class="competition_description">
				${description }
			</div>
 			
 			<h2>${title }作品列表</h2>
			<div class="soft_content">
			<ul>
				<s:iterator value="recordList" status="st" >
					<li class='<s:if test="%{#st.odd==true }">one</s:if><s:else>two</s:else>'>
						<div>
							<span>参赛作品:<a href="soft_detail.action?id=${id }">${title }</a></span>
						</div>
						<table>
							<tr>
								<td>参赛人:<a href="">${user.username }</a></td>
								<td>排名:${st.count+(currentPage-1)*pageSize}<span></span></td>
								<td>最后得分:${score.scores }</td>
								<td>上传时间:${createdate }</td>
								<td><input type="button" class="likebutton2 download_button"  value="下载"/></td>
							</tr>
							<form action="soft_download.action" id="form${id }" method="post">
								<s:hidden name="id"></s:hidden>
							</form>
						</table>
						
					</li>
				</s:iterator>
			</ul>
		</div>

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
						 window.location.href="competition_detail.action?pageNum="+pageNum+"&id=${id}";
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
		</div>
	</div>
	<!--竞赛详情结束  -->
	
	<!--底部版权开始11  -->
	<%@ include file="/WEB-INF/jsp/public/bottom.jspf" %>
	<!--底部版权结束  -->

</body>
</html>