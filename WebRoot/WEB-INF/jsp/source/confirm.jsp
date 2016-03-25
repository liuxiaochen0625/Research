<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>注册激活</title>
	<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/confirm.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_confirmPage.js"></script>
</head>
<body>
	<!-- 注册激活div -->
	<div class="confirm_div" id="confirm_div">
		<div class="confirm_title">
			<span>注册/激活</span>
		</div>
		<div class="confirm_content">
			<h1>查收确认信</h1>
			<p class="p1">
				确认信已经发送到你的邮箱
				<span id="email">
					${mail }
				</span>
				,你需要点击邮件中的确认连接来完成注册
			</p>
		</div>
		<div class="confirm_help">
			<h2>
				没有收到确认信怎么办？......
			</h2>
			<form action="" id="resend_form" method="post">
				<ul>
					<li>
						检查一下上面Email地址是否正确，错了就
						<a href="user_addUI.action">
							重新注册
						</a>
						一次吧:)
					</li>
					<li>
						看看是否在邮箱的垃圾箱里
					</li>
					<li>
						稍等几分钟，若仍旧没收到确认信，让服务器
						<a id="a_resend" href="javascript:void(0)" >
						    重发一封
						</a>
					</li>
				</ul>
				<s:hidden id="activeCode" name="activeCode" value="%{#user.activeCode}"></s:hidden>
			</form>
			
		</div>
		
	</div>
	<!-- 注册激活div -->


</body>
</html>