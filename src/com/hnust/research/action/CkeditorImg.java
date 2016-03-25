package com.hnust.research.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hnust.research.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class CkeditorImg extends ActionSupport{
	private File upload;//文件
	private String uploadFileName;//文件名
	private String uploadContentType;//文件类型
	private String callback;
	
	/**
	 * 验证文件的合法性
	 * 处理ckeditor上传的图片，根据不同的用户，生成不同的文件夹,
	 * 路径格式：upload/username(用户名登录名)/201407(年月)/112131541132123.jpg
	 * 参考了网上csdn里的面一个同志的博客写出来的，这个execute里面并不要配置返回页面
	 * 这个上传文件的方法是给前台用户设计的,每个用户会根据自己的用户名和上传时间建立一个文件夹
	 */
	public String execute() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		
		// CKEditor提交的很重要的一个参数 
		callback=ServletActionContext.getRequest().getParameter("CKEditorFuncNum");
		User user=(User) ActionContext.getContext().getSession().get("user");
		if(user!=null){
			String expandName=uploadFileName.substring(uploadFileName.lastIndexOf(".")).toLowerCase();
			if(expandName.equals(".png")||expandName.equals(".gif")||expandName.equals(".bmp")||expandName.equals(".jpg")){//检查扩展名是否合法,bmp,jpg,png,gif
				if(upload.length()>600*1024){//文件超过600K
					out.println("<script type=\"text/javascript\">");    
		            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'图片大小不能超过600k');");   
		            out.println("</script>");  
		            return null;  
				}
				//进行文件上传操作
				InputStream is=new FileInputStream(upload);
				Date date=new Date();
				SimpleDateFormat formatter=new SimpleDateFormat("yyyyMM");
				String yyyymm=formatter.format(date);
				String fileName=java.util.UUID.randomUUID().toString()+expandName;
				String uploadPath=ServletActionContext.getServletContext().getRealPath("\\upload")+"\\images\\"+user.getUsername()+"\\"+yyyymm+"\\";
				String src="/Research/upload/"+"images/"+user.getUsername()+"/"+yyyymm+"/";
				File file=new File(uploadPath);
				if(!file.exists()){//不存在就创建这个目录
					file.mkdirs();
				}
				File toFile=new File(uploadPath,fileName);
				OutputStream os=new FileOutputStream(toFile);
				byte[] buffer=new byte[1024];
				int length=0;
				while((length=is.read(buffer))>0){
					os.write(buffer,0,length);
				}
				is.close();
				os.close();
				//上传成功之后,返回"图像"选项卡并显示图片
				System.out.println(uploadPath+fileName);
				 out.println("<script type=\"text/javascript\">"); 
			     out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + src+fileName+ "','')");    
			     out.println("</script>");
			     return null;
			}else{//输入文件名不合法信息,这里是调用ckeditor自带的callFunction方法返回信息
				out.println("<script type=\"text/javascript\">");
				out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
				out.println("</script>");
				return null;
			}
		}else{
			System.out.println("未登录用户");
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'请先登录再上传图片');");
			out.println("</script>");
			return null;
		}
	}
	
	
	public String adminImg() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		String expandName=uploadFileName.substring(uploadFileName.lastIndexOf(".")).toLowerCase();
		
		// CKEditor提交的很重要的一个参数 
		callback=ServletActionContext.getRequest().getParameter("CKEditorFuncNum");
		if(expandName.equals(".png")||expandName.equals(".gif")||expandName.equals(".bmp")||expandName.equals(".jpg")){//检查扩展名是否合法,bmp,jpg,png,gif
			if(upload.length()>600*1024){//文件超过600K
				out.println("<script type=\"text/javascript\">");    
	            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'图片大小不能超过600k');");   
	            out.println("</script>");  
	            return null;  
			}
			//进行文件上传操作
			InputStream is=new FileInputStream(upload);
			Date date=new Date();
			SimpleDateFormat formatter=new SimpleDateFormat("yyyyMM");
			String yyyymm=formatter.format(date);
			String fileName=java.util.UUID.randomUUID().toString()+expandName;
			String uploadPath=ServletActionContext.getServletContext().getRealPath("\\upload")+"\\admin\\images\\";
			String src="/Research/upload/admin/images/";
			File file=new File(uploadPath);
			if(!file.exists()){//不存在就创建这个目录
				file.mkdirs();
			}
			File toFile=new File(uploadPath,fileName);
			OutputStream os=new FileOutputStream(toFile);
			byte[] buffer=new byte[1024];
			int length=0;
			while((length=is.read(buffer))>0){
				os.write(buffer,0,length);
			}
			is.close();
			os.close();
			//上传成功之后,返回"图像"选项卡并显示图片
			System.out.println(uploadPath+fileName);
			 out.println("<script type=\"text/javascript\">"); 
		     out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + src+fileName+ "','')");    
		     out.println("</script>");
		     return null;
		}else{//输入文件名不合法信息,这里是调用ckeditor自带的callFunction方法返回信息
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
			out.println("</script>");
			return null;
		}
	}
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	 
}
