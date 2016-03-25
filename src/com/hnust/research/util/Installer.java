package com.hnust.research.util;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hnust.research.domain.Comment;
import com.hnust.research.domain.Label;
import com.hnust.research.domain.ProgLanguage;
import com.hnust.research.domain.Score;
import com.hnust.research.domain.Source;
import com.hnust.research.domain.User;

/**
 * 安装程序
 * 向数据库中插入编程语言,所属类型
 * 此程序只执行一次就好了
 * @author Administrator
 *
 */
@Component
public class Installer {
	@Resource
	private SessionFactory sessionFactory;
	@Transactional
	public void install(){
		Session session=sessionFactory.getCurrentSession();

		//插入语言
		session.save(new ProgLanguage("Java"));
		session.save(new ProgLanguage("C/C++"));
		session.save(new ProgLanguage("PHP"));
		session.save(new ProgLanguage("C#"));
		session.save(new ProgLanguage(".NET"));
		session.save(new ProgLanguage("ASP"));
		session.save(new ProgLanguage("Javascript"));
		session.save(new ProgLanguage("HTML/CSS"));
		session.save(new ProgLanguage("Python"));
		session.save(new ProgLanguage("ObjectiveC"));
		session.save(new ProgLanguage("Ruby"));
		session.save(new ProgLanguage("Pascal"));
		session.save(new ProgLanguage("汇编"));
		session.save(new ProgLanguage("Swift"));
		session.save(new ProgLanguage("Groovy"));
		session.save(new ProgLanguage("Scala"));
		session.save(new ProgLanguage("VB"));
		
		//插入类型
		session.save(new Label("android"));
		session.save(new Label("ajax"));
		session.save(new Label("bootstrap"));
		session.save(new Label("css"));
		session.save(new Label("extjs"));
		session.save(new Label("hibernate"));
		session.save(new Label("ios"));
		session.save(new Label("ibatis"));
		session.save(new Label("json"));
		session.save(new Label("Jquery"));
		session.save(new Label("JqueryUI"));
		session.save(new Label("jbpm"));
		session.save(new Label("linux"));
		session.save(new Label("mysql"));
		session.save(new Label("mail"));
		session.save(new Label("oracle"));
		session.save(new Label("ssh框架"));
		session.save(new Label("图形化编程"));
		session.save(new Label("strtus2"));
		session.save(new Label("sqlserver"));
		session.save(new Label("微信"));
		session.save(new Label("毕业设计"));
		session.save(new Label("在线编辑器"));
		session.save(new Label("博客系统"));
		session.save(new Label("网路爬虫"));
		session.save(new Label("搜索引擎"));
		session.save(new Label("实用小软件"));
		session.save(new Label("分页技巧"));
		session.save(new Label("网络编程"));
		session.save(new Label("小游戏"));
		session.save(new Label("前端界面"));
		
	}
	
	@Transactional
	public void save25Users(){
		Session session=sessionFactory.getCurrentSession();
		for(int i=0;i<25;i++){
			User user=new User();
			user.setUsername("chenye"+i);
			user.setPassword(DigestUtils.md5Hex("123456"));
			user.setSharecount(new Random().nextInt(50));
			user.setCreatedate(new Date());
			user.setDescription("大家好我是chenye"+i);
			user.setNickname("chenye"+i);
			user.setSex(1);
			user.setActive(1);
			user.setImgpath("/Research/images/source/default.jpg");
			user.setMail("123132112323"+i+"@qq.com");
			session.save(user);
		}
	}
	
	@Transactional
	public void save50Sources(){
		Session session=sessionFactory.getCurrentSession();
		for(int i=0;i<50;i++){
			Source source=new Source();
			source.setTitle("source"+i);
			source.setDescription("description"+i);
			source.setStatus(i%2==0?1:0);
			session.save(source);
		}
	}
	
	public static void Test(Object object){
		System.out.println(object.getClass().getSimpleName().toLowerCase());
	}
	
	public static void main(String[] args){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer=(Installer) ac.getBean("installer");
		installer.save25Users();
		installer.install();
		installer.save50Sources();
		System.out.println(new Score(1, 3, 5, 7, 9,"1,3,5,7").existsId((long)2));
		Comment comment=new Comment();
		Test(comment);
	}
}
