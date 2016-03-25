<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/login.css">
	<title>后台管理员登陆</title>
</head>
<body>
	<form>
		<div class="loginPanel">
			<div class="x-box-tl">
				<div class="x-box-tr">
					<div class="x-box-tc">
					</div>
				</div>
			</div>

			<div class="x-box-ml">
				<div class="x-box-mr">
					<div class="x-box-mc" style="height: 173px;">
						<img id="j_id2:j_id4" src="${pageContext.request.contextPath}/images/admin/login/register.png"/>
						<table id="j_id2:j_id5" cellspacing="3px" style="width:100%">
							<tr>
								<td align="right" colspan="1" rowspan="1" style="padding-right: 3px;">
									<label>用户名：</label>
								</td>
								<td colspan="2">
									<label><input type="text" style="width: 212px;" class="x-form-text"/></label>
								</td>
							<tr>

							<tr>
								<td align="right" colspan="1" rowspan="1" style="padding-right: 3px;">
									<label>密码：</label>
								</td>
								<td colspan="2">
									<label><input type="password" style="width: 212px;" class="x-form-text"/></label>
								</td>
							<tr>

							<tr>
								<td align="right"  style="padding-right: 3px;">
									<label>验证码：</label>
								</td>
								<td>
									<label><input type="text" style="width: 70px;" class="x-form-text"/></label>
								</td>
								<td  style="padding-right: 60px;">
									<label>
										<img src="${pageContext.request.contextPath}/images/admin/login/login.jpg" height="20" width="60" style="cursor:pointer;" title="点击更换" />
									</label>
								</td>
							<tr>

							<tr>
								<td align="center" colspan="2">
									<label>
										<a href="home_index.action">
											<img src="${pageContext.request.contextPath}/images/admin/login/ZR05.gif" style="padding-left:42px;"/>
										</a>
									</label>
								</td>
								<td style="padding-right: 20px;">
									<label style="color:red">密码错误!</label>
								</td>
							<tr>
						</table>
					</div>
				</div>
			</div>

			<div class="x-box-bl">
				<div class="x-box-br">
					<div class="x-box-bc">
					</div>
				</div>
			</div>

			<div class="foot">湖南科技大学计算机学院版权所有</div>
		</div>
	</form>
</body>
</html>