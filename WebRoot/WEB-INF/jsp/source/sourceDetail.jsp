<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>资源详情</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/source.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/source_detail.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_comment.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_sourceDetailPage.js"></script>
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
	
	<!--隐藏域  -->
	<s:hidden id="userId" value="%{#session.user.id}" name="userId"></s:hidden>
	<s:hidden id="flag" value="source" name="flag"></s:hidden>
	<s:hidden id="toId" name="id"></s:hidden>
	<!--隐藏域结束  -->
	
	<!-- 单个资源详细样式 -->
	<div class="source_detail">
		<div class="detail_title">
			<ul>
				<li>
					<a href="">项目列表</a>
				</li>
				<li>/</li>
				<li>
					<a href="">${source.title }</a>
				</li>
			</ul>
		</div>

		<div class="detail_content">
			<div class="content_top">
				<div class="left_img">
					<!--上传者头像  -->
					<img src="${source.user.imgpath}" alt="" widht="40"/>
					<!--上传者头像结束  -->
				</div>
				<div class="right_content">
					<span>
						<!--资源标题  -->
						<a href="source_detail.action?id=${id}">${source.title }</a>
						<!--资源标题结束  -->
					</span>
					<ul>
						<li>
							<!--上传者nickname  -->
							<a href="">${source.user.nickname}</a>
							<!--上传者nickname结束  -->
						</li>
						
						<!--发布时间  -->
						<li>发布于${source.time }</li>
						<!--发布时间结束  -->
						
						<!--下载次数  -->
						<li>
							<em>${source.count}</em>次下载
						</li>
						<!--下载次数结束  -->
						
						<!--所属语言  -->
						<li>
							语言: 
							<a href="">${source.language }</a>
						</li>
						<!--所属语言结束  -->
						
						<!--类型  -->
						<li>类型:
							<a href="">${source.type }</a>
						</li>
						<!--类型结束  -->
					</ul>
				</div>
			</div>

			<!--描述区域  -->
			<div class="content_middle">
				${source.description }
			</div>
			<!--描述区域结束  -->

			<div class="content_bottom">
				<s:form name="downloadform" id="downloadform" action="source_download.action">
					<s:hidden name="id" id="id"></s:hidden>
					<input type="button" class="likebutton" id="download" value="下载" />
				</s:form>
			</div>
			
			<!-- 评论区域开始 -->
			<div id="comments_div">
				<div>发表评论</div>
				<br/>
				<div class="create_comment">
					<textarea name="comment" id="comment" style="resize:none;width:760px;height:90px"></textarea>
					<div>
						<br/>
						<input type="button" value="发表" class="likebutton" style="float:right" id="comment_button"/>
					</div>
				</div>
	
				<div class="comments_list">
					<ul id="comment_ul">
						<s:iterator value="commentList">
							<li class="comments_item">
								<div class="comments_item_bd">
									<div class="ui_avatar">
										<a href=""><img src="${user.imgpath }" alt="" width="35" height="35"/></a>
									</div>
									<div class="comments_content">
										<a href="">${user.username }</a>
										:${comment }
										<div class="comments_op">
											<span>${datetime}</span>
										</div>
									</div>
								</div>
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>			
			<!-- 评论区域结束 -->
		
		</div>
	</div>
	<!-- 单个资源详细样式 -->
	
	<!--底部版权开始11  -->
	<%@ include file="/WEB-INF/jsp/public/bottom.jspf" %>
	<!--底部版权结束  -->
</body>
</html>