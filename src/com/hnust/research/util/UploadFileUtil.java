package com.hnust.research.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hnust.research.domain.Soft;
import com.hnust.research.domain.Source;
import com.hnust.research.domain.User;
import com.hnust.research.service.SourceService;
import com.hnust.research.service.UserService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 文件上传帮助类
 * @author Administrator
 *
 */
@Component
@Scope("prototype")
public class UploadFileUtil {
	@Resource
	private UserService userService;
	
	@Resource
	private SourceService sourceService;
	
	
	/**
	 * 上传多个文件的方法
	 * @param upload
	 * @param uploadFileNames
	 * @param PATH
	 */
	public void upload(List<File> upload,List<String> uploadFileNames,String PATH){
		
	}
	
	/**
	 *上传头像,
	 *1.要对文件大小进行验证,不能超过2M,超过了就抛出FileBeyondSize的自定义异常,只要在UserAction中抓异常就可以了。
	 *2.
	 */
	public void uploadImg(File upload,String uploadFileName,String PATH){
		if(upload!=null){//判断文件是否为空
			User user=(User)ActionContext.getContext().getSession().get("user");
			//获取后缀名
			int index=uploadFileName.indexOf('.');
			String houzhui=uploadFileName.substring(index);
			//生成当前日期long型加上随机数
			Long time=new Date().getTime();
			Random random=new Random();
			int number=random.nextInt(1000);
			//存储的是一个随机的名字
			String filepath=Long.toString(time)+Integer.toString(number)+houzhui;

			//将图片放到用户专有文件夹下面
			PATH=PATH+"//"+user.getUsername()+"//";
			InputStream is=null;
			OutputStream os=null;
			try {
				is=new FileInputStream(upload);
				File file=new File(PATH);
				//创建文件输出流
				if(!file.exists()){
					file.mkdirs();
				}
				os=new FileOutputStream(PATH+filepath);
				//缓存字节
				byte[] buffer=new byte[1024];
				int count=0;
				while((count=is.read(buffer))>0){
					os.write(buffer, 0, count);
				}		
				//更新
				user.setImgpath("/Research/images/source/"+user.getUsername()+"/"+filepath);
				userService.update(user);
				//最后更新session
				ActionContext.getContext().getSession().put("user", user);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(os!=null){
						os.flush();
						os.close();
					}
					if(is!=null){
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 用户上传资源的方法
	 *上传资源的时候,上传者的分享次数要+1
	 * @param user
	 * @param file
	 * @param userId
	 */
	public void uploadSource(Source source,File file,User user,String fileName){
		/**
		 * 第一步，数据准备
		 */
		//插入数据,之前已经封装好了title,description,type,language;这里需要对description进行重新设置,转换编码
		SimpleDateFormat formater=new SimpleDateFormat("yyyyMM");
		Date time=new Date();
		String yyyymm=formater.format(time);
		//资源的路径（upload/source/username/201407/XXXXXXXXXXX.zip(or rar)）
		String path=ServletActionContext.getServletContext().getRealPath("\\resource")+"\\source\\"+user.getUsername()+"\\"+yyyymm+"\\";
		Integer count=0;//下载次数
		Integer status=0;//状态0为未审核
		//开始设值
		try {
			source.setDescription(URLDecoder.decode(source.getDescription(),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		source.setCount(count);
		source.setPath(path+fileName);
		source.setStatus(status);
		source.setTime(time);
		source.setUser(user);
		sourceService.save(source);
		
		/**
		 * 第二步,作者分享次数+1
		 */
		user.setSharecount(user.getSharecount()+1);
		userService.update(user);
		
		/**
		 * 第三步，文件上传
		 */
		InputStream is=null;
		OutputStream os=null;
		try {
			is=new FileInputStream(file);
			File newFile=new File(path);
			if(!newFile.exists()){
				newFile.mkdirs();
			}
			os=new FileOutputStream(path+fileName);
			byte[] buffer=new byte[1024];
			int length=0;
			while((length=is.read(buffer))>0){
				os.write(buffer, 0, length);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(is!=null){
					is.close();
				}
				if(os!=null){
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static void uploadSoft(User user,Soft soft,File file,String fileFileName){
		//上传目录(Research/soft/(用户名)/(文件名)
		String path=ServletActionContext.getServletContext().getRealPath("\\soft")+"\\"+user.getUsername()+"\\";
		InputStream is=null;
		OutputStream os=null;
		try {
			is=new FileInputStream(file);
			File newFile=new File(path);
			if(!newFile.exists()){
				newFile.mkdirs();
			}
			os=new FileOutputStream(path+fileFileName);
			System.out.println("path="+path);
			byte[] buffer=new byte[1024];
			int length=0;
			while((length=is.read(buffer))>0){
				os.write(buffer, 0, length);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(is!=null){
					is.close();
				}
				if(os!=null){
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
