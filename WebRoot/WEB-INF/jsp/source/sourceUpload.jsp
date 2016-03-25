<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<title>上传资源</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/source.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/upload.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_uploadPage.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js" charset="utf-8"></script>
	<!--ckeditor的配置使用  -->
	<script type="text/javascript">
		CKEDITOR.editorConfig = function( config )
		{
			config.language = 'zh-cn'; //配置语言    
			config.width = 705; //宽度    
			config.height = 250; //高度    
			config.skin = 'v2'; //编辑器皮肤样式
			config.resize_enabled = false;  //取消拖拽改变大小
			//自定义工具栏
			config.toolbar =
			    [
			       ['Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript'],
			       ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'Blockquote'],
			       ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
			       ['Link', 'Unlink', 'Anchor'],
			       ['Image', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak'],
			       '/',
			       ['Styles', 'Format', 'Font', 'FontSize'],
			       ['TextColor', 'BGColor'],
			       ['Maximize', 'ShowBlocks', '-', 'Source', '-', 'Undo', 'Redo']
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
			config.filebrowserImageUploadUrl = 'ck/ck_Img.action',
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
				<li><a href="source_uploadUI.action" class="selected">我要上传</a></li>
				<li><a href="competition_list.action"  >创新竞赛</a></li>
				<li><a href="competition_attendUI.action" >参加竞赛</a></li>
			</ul>
			<%@ include file="/WEB-INF/jsp/public/head.jspf" %>
		</div>
	</div>
	<!-- 头部结束 -->

	<!--上传区域 -->
	<div id="upload_div" >
		<p>资源上传</p>
		<hr />
		<div class="upload_main">
				<div class="upload_left">
					<s:form name="sourceform" method="post" id="sourceform" enctype="multipart/form-data" action="source_upload.action">
						<!-- 标题区域 -->
						<div class="title_div">
							<span>标题:</span>
							<input type="text" name="title" id="title"/>
						</div>
						<!-- 标题区域结束 -->
		
						<!-- 描述区域 -->
						<span class="description_span">描述:</span>
						<div class="description_div">
							<textarea name="description" id="description" cols="60" rows="30" class="ckeditor"> </textarea>
						</div>
						<!-- 描述区域结束 -->
		
						<!-- 类型区域 -->
						<div class="type_div" >
							<div class="type_div_left">
								<span>编程语言:</span>
								<s:select name="language" id="language" cssClass="language" list="languageList" listKey="language" listValue="language"
								headerKey="--------" headerValue="--------" />

							</div>
							<div class="type_div_right">
								<span>类型:</span>
								<s:select cssClass="type" name="type" list="labelList" listKey="type" listValue="type" headerKey="--------" headerValue="--------" />
							</div>
						</div>
						<!-- 类型区域结束 -->
						
		
						<!-- 文件选择区域 -->
						<div class="file_div">
							<div class="choose_file">
								<a href="javascript:void(0)">选择文件</a>
								<input type="file" name="file" id="file"/>
								<span class="help">(提示:请选择要上传的资源,打好包,格式为zip或者rar,限制在20M以内)</span>
								<!-- 文件选择信息展示 -->
								<div id="file_info">
									<span>当前选择的文件:</span>
								</div>
								<!-- 文件信息展示区域 -->
							</div>
						</div>
						<!--文件选择区域  -->
					</s:form>
					
					<!-- 发送区域 -->
					<div class="send_div">
						<input type="button" class="likebutton"  value="上传" id="toUpload"/>
					</div>
					<!-- 发送区域结束 -->
					
					<!-- 遮盖层div -->
					<!-- 由jquery动态添加html代码 -->
					<!-- 遮盖层div结束 -->
					
				</div>
				<div class="upload_right">
					<ul class="rule">
						<li>说明</li>
						<li>1,请填写资源的标题。</li>
						<li>2,请详细描述资源。</li>
						<li>3,在ckeditor编辑器中可以插入图片说明项目。</li>
						<li>4,请将资源打好包(rar,zip)。</li>
						<li>5,总文件限制在30M以内。</li>
					</ul>
				</div>
		</div>
	</div>
	<!-- 上传区域结束 -->
	
	
	<!--底部版权开始11  -->
	<%@ include file="/WEB-INF/jsp/public/bottom.jspf" %>
	<!--底部版权结束  -->
</body>
</html>