<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/left.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/admin/left.js"></script>
	<title>左边</title>
</head>
<body>
	<ul id="Menu">
		<li class="level">
			<div class="levelStyle">
				<img src="${pageContext.request.contextPath}/images/admin/left/user.png" alt="" />
				<a href="javascript:void(0)">用户管理</a>
			</div>
			<ul class="MenuItem">
				<li>
					<div class="level2Style">
						<img src="${pageContext.request.contextPath}/images/admin/left/arrow_green.png" alt="" />
						<a href="${pageContext.request.contextPath}/admin/user_list.action" target="right">
							用户列表
						</a>
					</div>
				</li>
			</ul>
		</li>

		<li class="level">
			<div class="levelStyle">
				<img src="${pageContext.request.contextPath}/images/admin/left/source.png" alt="" />
				<a href="javascript:void(0)">资源管理</a>
			</div>
			<ul class="MenuItem">
				<li>
					<div class="level2Style">
						<img src="${pageContext.request.contextPath}/images/admin/left/arrow_green.png" alt="" />
						<a href="${pageContext.request.contextPath}/admin/source_listOk.action" target="right">
							已经审核
						</a>
					</div>
				</li>
				<li>
					<div class="level2Style">
						<img src="${pageContext.request.contextPath}/images/admin/left/arrow_green.png" alt="" />
						<a href="${pageContext.request.contextPath}/admin/source_listUnchecked.action" target="right">
							待我审核
						</a>
					</div>
				</li>
				<li>
					<div class="level2Style">
						<img src="${pageContext.request.contextPath}/images/admin/left/arrow_green.png" alt="" />
						<a href="${pageContext.request.contextPath}/admin/language_list.action" target="right">
							资源语言
						</a>
					</div>
				</li>
				<li>
					<div class="level2Style">
						<img src="${pageContext.request.contextPath}/images/admin/left/arrow_green.png" alt="" />
						<a href="${pageContext.request.contextPath }/admin/label_list.action" target="right">
							资源类型
						</a>
					</div>
				</li>
			</ul>
		</li>

		<li class="level">
			<div class="levelStyle">
				<img src="${pageContext.request.contextPath}/images/admin/left/competition.png" alt="" width="16" height="16"/>
				<a href="javascript:void(0)">竞赛管理</a>
			</div>
			<ul class="MenuItem">
				<li>
					<div class="level2Style">
						<img src="${pageContext.request.contextPath}/images/admin/left/arrow_green.png" alt="" />
						<a href="${pageContext.request.contextPath}/admin/competition_createUI.action" target="right">
							发起竞赛
						</a>
					</div>
				</li>
				<li>
					<div class="level2Style">
						<img src="${pageContext.request.contextPath}/images/admin/left/arrow_green.png" alt="" />
						<a href="${pageContext.request.contextPath}/admin/competition_listStart.action" target="right">
							正在进行
						</a>
					</div>
				</li>
				<li>
					<div class="level2Style">
						<img src="${pageContext.request.contextPath}/images/admin/left/arrow_green.png" alt="" />
						<a href="${pageContext.request.contextPath}/admin/competition_listEnd.action" target="right">
							已经结束
						</a>
					</div>
				</li>
			</ul>
		</li>

		<li class="level">
			<div class="levelStyle">
				<img src="${pageContext.request.contextPath}/images/admin/left/notice.png" alt="" width="16" height="16"/>
				<a href="javascript:void(0)">系统公告</a>
			</div>
			<ul class="MenuItem">
				<li>
					<div class="level2Style">
						<img src="${pageContext.request.contextPath}/images/admin/left/arrow_green.png" alt="" />
						<a href="${pageContext.request.contextPath}/admin/notice_createUI.action" target="right">
							发布公告
						</a>
					</div>
				</li>
				<li>
					<div class="level2Style">
						<img src="${pageContext.request.contextPath}/images/admin/left/arrow_green.png" alt="" />
						<a href="${pageContext.request.contextPath}/admin/notice_list.action" target="right">
							公告列表
						</a>
					</div>
				</li>
			</ul>
		</li>

		<li class="level">
			<div class="levelStyle">
				<img src="${pageContext.request.contextPath}/images/admin/left/message.gif" alt="" width="16" height="16"/>
				<a href="javascript:void(0)">评论管理</a>
			</div>
			<ul class="MenuItem">
				<li>
					<div class="level2Style">
						<img src="${pageContext.request.contextPath}/images/admin/left/arrow_green.png" alt="" />
						<a href="${pageContext.request.contextPath}/admin/comment_list.action" target="right">
							评论列表
						</a>
					</div>
				</li>
			</ul>
		</li>
	</ul>
</body>
</html>