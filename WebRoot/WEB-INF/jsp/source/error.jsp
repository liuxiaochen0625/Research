<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css" />
	<title>系统繁忙</title>
	<link rel="stylesheet" href="./css/error.css" />
</head>
<body>
	<div class="system_error">
		<div class="error_title">
			<span>
				系统繁忙
			</span>
		</div>
		<div class="error_content">
			<span>
				对不起,系统繁忙.
			</span>
			<span >
				<a href="javascript:history.go(-1)" style="color:#3F9207">上一步</a>
			</span>
		</div>
	</div>
</body>
</html>