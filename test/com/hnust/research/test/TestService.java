package com.hnust.research.test;

import javax.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.hnust.research.domain.User;


@Component("testService")
public class TestService {

	@Resource
	private SessionFactory sessionFactory;
	@Transactional
	public void saveTwoUsers() {
		Session session=sessionFactory.getCurrentSession();
		session.save(new User());
//		int a=1/0;
		session.save(new User());
	}

}
