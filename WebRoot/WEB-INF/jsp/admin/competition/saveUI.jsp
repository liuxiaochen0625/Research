<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demos.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.ui.all.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/pageCommon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/competition_create.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/admin/jquery.ui.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/admin/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/admin/jquery.ui.widget.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js" charset="utf-8"></script>
	<script type="text/javascript">
	CKEDITOR.editorConfig = function( config )
		{
			config.language = 'zh-cn'; //配置语言    
			config.width = 680; //宽度    
			config.height = 230; //高度    
			config.skin = 'v2'; //编辑器皮肤样式
			config.resize_enabled = true;  //取消拖拽改变大小
			//自定义工具栏
			config.toolbar =
			    [
			       ['Bold', 'Italic', 'Underline', 'Strike', '-'],
			       ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'Blockquote'],
			       ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
			       ['Link', 'Unlink', 'Anchor'],
			       ['Image', 'SpecialChar', 'PageBreak'],
			       '/',
			       [ 'Format', 'Font', 'FontSize'],
			       ['TextColor', 'BGColor'],
			       ['Maximize', '-', 'Source', '-', 'Undo', 'Redo']
			    ];      
	
			//字体的定义
			
			// 在 CKEditor 中集成 CKFinder注意 ckfinder 的路径选择要正确。   
			config.filebrowserImageBrowseUrl = '${pageContext.request.contextPath}/plugins/ckfinder/ckfinder.html?type=Images',//浏览图片的服务器的链接,对一般用户不用开放这个权限
			config.filebrowserImageUploadUrl = '${pageContext.request.contextPath}/ck/ck_adminImg.action',//这个是上传图像的url配置
			config.filebrowserWindowWidth = '1000',
			config.filebrowserWindowHeight = '700'
		};
	</script>
	<script type="text/javascript">
			$(function(){
				$("#datepicker").datepicker({
					numberOfMonths:1,
					showButtonPanel:true,
					dateFormat:'yy-mm-dd',
					minDate: 0
				})
				$(".save").click(function(){
					$(".error").remove();
					var title=$(".title1").val();
					var description=CKEDITOR.instances.description.getData();
					var date=$("#datepicker").val();
					if(title.length<=0){
						var html=$("<span class='error'>标题不能为空</span>");
						$(".title1").after(html);
					}
					if(description.length<=0){
						var html=$("<span class='error'>描述不能为空</span>")
						$(".a2").before(html);
					}
					if(date.length<=0){
						var html=$("<span class='error'>日期不能为空</span>")
						$("#datepicker").after(html)
					}
					if($(".error").length<=0){
						$("#competition_form").submit();
					}
				})
			})
	</script>
	<title>发布竞赛</title>
</head>
<body>
	<!-- 标题部分 -->
	<div id="title_bar">
		<div id="title_bar_head">
			<div class="title_head"></div>
			<div class="title">
				<img src="${pageContext.request.contextPath}/images/admin/right/title_arrow.gif" border="0" height="13" width="13">
				<span>发布竞赛</span>
			</div>
			<div class="title_end"></div>
		</div>
	</div>
	<!-- 标题部分结束 -->
	
	<!-- 发布竞赛 -->
	<s:form name="create" action="competition_%{id==null?'create.action':'edit.action'}" method="post" id="competition_form">
		<s:hidden name="id"></s:hidden>
		<div class="wrapper">
			<div class="create">
				<b>发布竞赛</b>
				<div class="a1">
					<span class="s1">竞赛标题：</span>
					<s:textfield name="title" size="50" cssClass="title1"></s:textfield>
				</div>
				<div class="a1">
					<span class="s1">竞赛描述：</span>
					<div class="a2">
						<s:textarea cssClass="ckeditor" name="description" id="description" cols="30" rows="10"></s:textarea>
					</div>
				</div>
			
				<div class="a1">
					<span class="s1">结束时间：</span>
					<s:textfield name="end" id="datepicker" readonly="true"></s:textfield>
				</div>
	
			</div>
			<div class="operator">
				<img class="save" src="${pageContext.request.contextPath}/images/admin/right/save.png" />
				<img class="back" src="${pageContext.request.contextPath}/images/admin/right/goBack.png" onclick="javascript:history.go(-1)"/>
			</div>
		</div>
	</s:form>
	<!-- 发布竞赛结束 -->
</body>
</html>