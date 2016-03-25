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
	<title>评论列表</title>
</head>
<body>
	<!-- 标题部分 -->
	<div id="title_bar">
		<div id="title_bar_head">
			<div class="title_head"></div>
			<div class="title">
				<img src="${pageContext.request.contextPath}/images/admin/right/title_arrow.gif" border="0" height="13" width="13">
				<span>评论列表</span>
			</div>
			<div class="title_end"></div>
		</div>
	</div>
	<!-- 标题部分结束 -->
	
	<!-- 列表部分 -->
	<div class="user_list" style="padding-left:50px;paddding-right:50px;padding-top:2px;">
		<table border="0" cellspacing="0" cellpadding="0">
			<tr class="tableStyle tableTitle"  cellpadding="0" cellspacing="0">
				<td style="width:40px">Id</td>
				<td>toId</td>
				<td>内容</td>
				<td style="width:200px">时间</td>
				<td>类型</td>
				<td>评论人id</td>
				<td style="width:150px;">相关操作</td>
			</tr>

			<s:iterator value="recordList">
				<tr class="recordList">
					<td>${id }</td>
					<td>${toId }</td>
					<td>${comment }</td>
					<td>
						${datetime }
					</td>
					<td>
						${flag }
					</td>
					<td>
						${user.id }
					</td>
					<td>
						<a href="comment_delete.action?id=${id}&pageNum=${currentPage}" onclick="return confirm('确定要删除这个评论么？')">删除</a>
					</td>
				</tr>
			</s:iterator>
		</table>

		<div id="Page">
				<div class="pageInfo">
					<span>页次:${currentPage }/${pageCount }</span>
					<span>每页显示:${pageSize }条</span>
					<span>总记录数:${recordCount}条</span>
				</div>
				<ul class="pageSelect">
					<li class="first" onclick="gotoPage(1)">首页</li>
					<li class="prev" onclick="gotoPage(${currentPage-1})">上一页</li>
					<s:iterator begin="%{beginPageIndex}" end="%{endPageIndex}" var="num">
						<s:if test="#num==currentPage">
							<li class="current">${num }</li>
						</s:if>
						<s:else>
							<li class="pageNum" onclick="gotoPage(${num})">${num }</li>
						</s:else>
					</s:iterator>
					<li class="next" onclick="gotoPage(${currentPage+1})">下一页</li>
					<li class="end" onclick="gotoPage(${pageCount})">尾页</li>
				</ul>
			
				<div class="selectBar">
					<span>选择页数:</span>
					<select name="" class="xiala" id="xiala" onchange="gotoPage(this.value)">
						<s:iterator begin="1" end="pageCount" var="num">
							<option value="${num }">第${num }页</option>
						</s:iterator>
					</select>
				</div>
				<script type="text/javascript">
					function gotoPage(pageNum){
						if(pageNum==0){
							pageNum=1;
						}
						if(pageNum>${pageCount}){
							pageNum=${pageCount}
						}
						window.location.href="comment_list.action?pageNum="+pageNum;
					}
				</script>
				<script type="text/javascript">
					$("#xiala").val("${currentPage}");
				</script>
			</div>
		</div>
	</div>
	<!-- 列表部分结束 -->
</body>
</html>