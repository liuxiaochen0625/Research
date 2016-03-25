<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>参加竞赛</title>
	<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/source.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/attendUI.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_attendUI.js"></script>
	<script type="text/javascript">
	CKEDITOR.editorConfig = function( config )
		{
			config.language = 'zh-cn'; //配置语言    
			config.width = 680; //宽度    
			config.height = 230; //高度    
			config.skin = 'v2'; //编辑器皮肤样式
			config.resize_enabled = false;  //取消拖拽改变大小
			//自定义工具栏
			config.toolbar =
			    [
			       ['Bold', 'Italic', 'Underline', 'Strike', '-'],
			       ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'Blockquote'],
			       ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
			       ['Link', 'Unlink', 'Anchor'],
			       ['Image', 'Smiley', 'SpecialChar', 'PageBreak'],
			       '/',
			       [ 'Format', 'Font', 'FontSize'],
			       ['TextColor', 'BGColor'],
			       ['Maximize', '-', 'Source', '-', 'Undo', 'Redo']
			    ];      
			//自定义表情的使用
			config.smiley_path='plugins/ckeditor/plugins/smiley/images/wangwang/';
			config.smiley_images = ['1.gif', '2.gif', '3.gif', '4.gif', '5.gif', '6.gif', '7.gif', '8.gif', '9.gif', '10.gif', '11.gif',
			                        '12.gif', '13.gif', '14.gif', '15.gif','16.gif','17.gif','18.gif','19.gif','20.gif','21.gif','22.gif',
			                        '23.gif','24.gif','25.gif','26.gif','27.gif','28.gif','29.gif','30.gif','31.gif','32.gif','33.gif','34.gif',
			                        '35.gif','36.gif','37.gif','38.gif','39.gif','40.gif','41.gif','42.gif','43.gif','44.gif','45.gif','46.gif',
			                        '47.gif','48.gif','49.gif','50.gif','51.gif','52.gif','53.gif','54.gif','55.gif','56.gif','57.gif','58.gif',
			                        '59.gif','60.gif','61.gif','62.gif','63.gif','64.gif'
			                        ];
			//图片的描述
			config.smiley_descriptions=['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11',
			 	                        '12', '13', '14', '15','16','17','18','19','20','21','22',
				                        '23','24','25','26','27','28','29','30','31','32','33','34',
				                        '35','36','37','38','39','40','41','42','43','44','45','46',
				                        '47','48','49','50','51','52','53','54','55','56','57','58',
				                        '59','60','61','62','63','64'
			                            ]
			
			//字体的定义
			config.font_names = '宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;' + config.font_names;//這是基於默認字體的增加
			
			// 在 CKEditor 中集成 CKFinder注意 ckfinder 的路径选择要正确。   
			//config.filebrowserImageBrowseUrl = 'plugins/ckfinder/ckfinder.html?type=Images',//浏览图片的服务器的链接,对一般用户不用开放这个权限
			config.filebrowserImageUploadUrl = 'ck/ck_img.action',
			config.filebrowserWindowWidth = '1000',
			config.filebrowserWindowHeight = '700'
		};
	</script>
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
				<li><a href="competition_list.action" >创新竞赛</a></li>
				<li><a href="competition_attendUI.action" class="selected">参加竞赛</a></li>
			</ul>
			<%@ include file="/WEB-INF/jsp/public/head.jspf" %>
		</div>
	</div>
	<!-- 头部结束 -->
	
	<!-- 参加竞赛界面 -->
	<div class="attendUI_div_wrapper">
		<h2>参加竞赛</h2>
		<div class="attend_main">
			<form action="soft_upload.action" enctype="multipart/form-data" method="post" name="softUpload" id="softUpload">
				<div class="hidden_noteIds">
					<s:iterator value="#competitionList">
						<s:hidden name="noteIds" id="%{id}"></s:hidden>
					</s:iterator>
				</div>				
				
				<s:hidden name="userId" value="%{#session.user.id }" id="userId"></s:hidden>
				<div class="field">
					<label >选择竞赛:</label>
					<s:select list="#competitionList" name="competitionId" listKey="id" listValue="title" >
					</s:select>
				</div>

				<div class="field">
					<label >作品名字:</label>
					<input type="text" name="title" class="softname"/>
				</div>
				<div class="field">
					<label >作品描述:</label>
					<textarea name="description" class="ckeditor" id="description"></textarea>
				</div>
				<div class="field">
					<label >作品文件:</label>
					<input type="button" class="likebutton2" value="选择文件"/>
					<input type="file" class="choose_file" name="file"/>
					<div class="info">
					</div>
				</div>

				<div class="field">
					<input type="button" class="likebutton2" id="send" value="提交"/>
				</div>
			</form>
		</div>
		<div class="attend_rules">
			<ul>
				<li>说明</li>
				<li>1,必须先登陆才能参加竞赛。</li>
				<li>2,建议在描述的时候上传图片加以说明你的作品。</li>
				<li>3,上传的作品会经过管理员审核,如果审核未通过,我们会及时通知你。</li>
				<li>4,作品会经过大众投票评分,至比赛结束会宣布结果。</li>
			</ul>
		</div>
	</div>
	<!-- 参加竞赛界面 -->
	
	<!--底部版权开始11  -->
	<%@ include file="/WEB-INF/jsp/public/bottom.jspf" %>
	<!--底部版权结束  -->
</body>
</html>
