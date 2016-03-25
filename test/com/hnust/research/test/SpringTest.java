package com.hnust.research.test;

import java.util.Date;
import java.util.Random;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hnust.research.domain.Source;
import com.hnust.research.domain.User;
import com.hnust.research.service.SourceService;
import com.hnust.research.service.UserService;

public class SpringTest {
	private ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	
	
	/**
	 * 测试hibernate的SessionFactory是否被spring管理的
	 */
	@Test
	public void testSessionFactory() throws Exception{
		SessionFactory sessionFactory=(SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}
	
	/**
	 * 测试申明式事务管理
	 */
	@Test
	public void testTransaction() throws Exception{
		TestService testService=(TestService) ac.getBean("testService");
//		System.out.println(testService);
		testService.saveTwoUsers();
	}
	
	
}
