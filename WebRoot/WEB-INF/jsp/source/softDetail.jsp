<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>单个竞赛作品详情</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/source.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/soft_detail.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_softDetailPage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_comment.js"></script>
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
				<li><a href="source_uploadUI.action">我要上传</a></li>
				<li><a href="competition_list.action"  >创新竞赛</a></li>
				<li><a href="competition_attendUI.action" >参加竞赛</a></li>
			</ul>
			<%@ include file="/WEB-INF/jsp/public/head.jspf" %>
		</div>
	</div>
	<!-- 头部结束 -->
	
	
	<!-- 单个作品展示区域开始 -->
	<div class="wrapper_soft_detail">
		<h2>${title }</h2>
		<div class="soft_detail_content">
			<!-- 投票区域 -->
			<div class="vote">
				<span name="noteIds" value="${score.noteIds }" id="noteIds"></span>
				<div class="vote_left">
					<s:form action="soft_vote.action" name="vote_form" id="vote_form">
						<s:hidden name="id"></s:hidden>
						<dl class="item">
							<dt>
								请投上您宝贵的一票
								<span class="cf29">(必选)</span>
							</dt>
							<dd>
								<label>
									<input type="radio" name="level" value="5" class="radio" />
									5分  (这个作品很赞)
								</label>
							</dd>
							<dd>
								<label>
									<input type="radio" name="level" value="4" class="radio"/>
									4分  (还不错哦)
								</label>
							</dd>
							<dd>
								<label>
									<input type="radio" name="level" value="3" class="radio"/>
									3分  (勉勉强强啦)
								</label>
							</dd>
							<dd>
								<label>
									<input type="radio" name="level" value="2" class="radio"/>
									2分  (有待加油哦)
								</label>
							</dd>
							<dd>
								<label>
									<input type="radio" name="level" value="1" class="radio"/>
									1分  (没什么靓点哦)
								</label>
							</dd>
		
							<div>
								<!--<span class="login-tips">
									您还未登录,该投票需要<a href="">登录</a>才能参与
								</span> -->
								<s:hidden id="userId" value="%{#session.user.id}" name="userId"></s:hidden>
								<s:hidden id="flag" value="soft" name="flag"></s:hidden>
								<input type="button" class="likebutton2" value="投票" style="margin-left:60px;" id="vote_button"/>
							</div>
						</dl>
					</s:form>
				</div>
				
				<!-- 右边结果区域 -->
				<div class="vote_right">
					<dl class="item">
						<dt>
							投票结果
						</dt>
						<dd>
							<ul>
								<li>
									<div class="option">
										5分
									</div>
									<div class="bars">
										<div class="bar">
											<div class="percent" percent="${score.percent5 }" ></div>
										</div>
										<div class="data cf29">${score.percent5 }%</div>
									</div>
									<div class="counter">${score.level5 }票</div>
								</li>

								<li>
									<div class="option">
										4分
									</div>
									<div class="bars">
										<div class="bar">
											<div class="percent" percent="${score.percent4 }" ></div>
										</div>
										<div class="data cf29">${score.percent4 }%</div>
									</div>
									<div class="counter">${score.level4 }票</div>
								</li>

								<li>
									<div class="option">
										3分
									</div>
									<div class="bars">
										<div class="bar">
											<div class="percent" percent="${score.percent3 }" ></div>
										</div>
										<div class="data cf29">${score.percent3 }%</div>
									</div>
									<div class="counter">${score.level3 }票</div>
								</li>

								<li>
									<div class="option">
										2分
									</div>
									<div class="bars">
										<div class="bar">
											<div class="percent" percent="${score.percent2 }" ></div>
										</div>
										<div class="data cf29">${score.percent2 }%</div>
									</div>
									<div class="counter">${score.level2 }票</div>
								</li>

								<li>
									<div class="option">
										1分
									</div>
									<div class="bars">
										<div class="bar">
											<div class="percent" percent="${score.percent1 }" ></div>
										</div>
										<div class="data cf29">${score.percent1 }%</div>
									</div>
									<div class="counter">${score.level1 }票</div>
								</li>
							</ul>
						</dd>
					</dl>
				</div>
				<!-- 右边结果区域 -->
			</div>
			<!-- 投票区域结束 -->
			
			<!-- 作品的一些基本信息 -->
			<div class="basic_info">
				<div>
					<b>作品基本信息:</b>
				</div>
				<table>	
					<tr>
						<td>名称:<a href="" class="title">${title }</a></td>
						<td>排名:1</td>
						<td>得分:${score.scores }</td>
						<td>发布时间:${createdate }</td>
						<td></td>
					</tr>
					<tr>
						<td>作者:<a href="#####">${user.username }</a></td>
						<td>所属竞赛:<a href="competition_detail.action?id=${competition.id }">${competition.title }</a></td>
						<td> <input type="button" value="下载" class="likebutton2" id="download"/></td>
						<s:form action="soft_download.action" id="download_form" name="download_form">
							<s:hidden name="id" id="toId"></s:hidden>
						</s:form>
					</tr>
				</table>
			</div>
			<!-- 作品的一些基本信息结束 -->

			<!-- 作品描述 -->
			<div class="soft_description">
				<b>作品描述:</b>
				${description }
			</div>
			<!-- 作品描述 -->

			<!-- 评论区域开始 -->
			<div id="comments_div">
				<div>发表评论</div>
				<br/>
				<div class="create_comment">
					<textarea name="comment" id="comment" style="resize:none;width:760px;height:90px"></textarea>
					<div>
						<br/>
						<input type="button" value="发表" class="likebutton2" style="float:right" id="comment_button"/>
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
											<span>${datetime }</span>
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
	<!-- 单个作品展示区域结束 -->
	
	<!--底部版权开始11  -->
	<%@ include file="/WEB-INF/jsp/public/bottom.jspf" %>
	<!--底部版权结束  -->

</body>
</html>