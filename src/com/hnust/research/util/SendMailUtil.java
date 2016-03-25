package com.hnust.research.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

/**
 * 邮件发送辅助类，发送注册激活信息
 * @author Administrator
 *
 */
@Component
public class SendMailUtil {

	/**
	 * 
	 * @param to收件人
	 * @param url激活的地址
	 * from 发件人
	 * password发件人密码
	 * smtpHost服务器
	 * content内容
	 */
	public void sendMail(String to,String url){
		String smtpHost="smtp.qq.com";
		String from="2656483638@qq.com";
		String password="liu920625,./";
		String content="";
		content=createContent(content,to,url);
		Properties props=new Properties();
		props.setProperty("mail.smtp.auth", "true");  
        props.setProperty("mail.transport.protocol", "smtp");
        Session session=Session.getInstance(props);
        session.setDebug(true);
        
        Message msg=new MimeMessage(session);
        try {
			msg.setContent(content, "text/html;charset=utf-8");
			msg.setSubject("欢迎注册计算机学院研究所网站"); 
			msg.setFrom(new InternetAddress(from));
			
			Transport transport = session.getTransport();  
			transport.connect(smtpHost, 25, from.split("@")[0], password);  
			transport.sendMessage(msg,  
			        new Address[]{new InternetAddress(to)});  
			transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param to 发送给谁
	 * @param pass 更新的密码
	 * @param nickname 用户的名字
	 */
	public void sendMail1(String to,String pass,String nickname){
		String smtpHost="smtp.qq.com";
		String from="2656483638@qq.com";  
		String password="liu920625,./";
		String content="";
		content=createContent1(content, pass, nickname);
		Properties props=new Properties();
		props.setProperty("mail.smtp.auth", "true");  
        props.setProperty("mail.transport.protocol", "smtp");
        Session session=Session.getInstance(props);
        session.setDebug(true);
        
        Message msg=new MimeMessage(session);
        try {
			msg.setContent(content, "text/html;charset=utf-8");
			msg.setSubject("修改密码"); 
			msg.setFrom(new InternetAddress(from));
			
			Transport transport = session.getTransport();  
			transport.connect(smtpHost, 25, from.split("@")[0], password);  
			transport.sendMessage(msg,  
			        new Address[]{new InternetAddress(to)});  
			transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/**这个是生成的注册激活内容
     * 生成邮件html内容,这个你可以先写好静态的html界面，然后再复制这些内容到下面这个方法去构造html语句
     * @param content
     * @return
     */
    public static String createContent(String content,String to,String url){
    	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String date=formatter.format(new Date());
    	StringBuffer sb=new StringBuffer();
    	sb=sb.append("<div id='mailContentContainer' class='qmbox'>").append(
    	"<style>.qmbox #wrap{ width:814px ; border:1px solid #89C2F1; height:auto; margin:0px auto}").append(
    	".qmbox .middle { padding:20px 60px 10px 60px; height:auto; font-size:12px}</style>").append(
    	"<div id='wrap'>").append(
    	"<div class='middle' style='padding:0px; height:4px;border-bottom:none; width:814px;round:#d5e9f9; font-size:0px'></div>").append(
    	"<div style='width:814px;height:auto; padding-bottom:10px; padding-top:10px'>").append(
    	"<div style='padding-left:24px;'>亲爱的<a target='_blank' href='mailto:"+to+"'>"+to+"</a>，您好！</div><br>").append(
    	"<div style='padding-left:24px;'>已经收到了您的注册信息。请点击以下确认链接，立即注册研究所开源平台账号：</div><br>").append(
    	"<div style='text-align:center'><a target='_blank' style='margin:0px;font-size:18px;color:#249ff1' href='"+url+"'>点此激活,完成注册</a></div>").append(
    	"<br><div style='padding-left:24px;'>如果不能点击该链接地址，请复制并粘贴到浏览器的地址输入框</div><br>").append(
    	"<div style='padding-left:24px;'><a target='_blank' href='"+url+"' style='color:#249ff1'>"+url+"</a></div>").append(
    	"<br><div style='padding-left:24px;'>湖南科技大学计算机学院研究所<br>").append(
    	"<span style='border-bottom:1px dashed #ccc;'>"+date+"</span></div></div></div></div>");
    	content=sb.toString();
    	return content;
    }

    public static String createContent1(String content,String pass,String nickname){
    	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String date=formatter.format(new Date());
    	content="<div style='font-size:14px'>"+
    	"尊敬的"+nickname+",你的密码是<b id='pass'>"+pass+"</b>"+
    	"<br><br>"+
    	"湖南科技大学计算机学院研究所"+"<br><br>"+date+"</div>";
    	return content;
    				
    }
}
