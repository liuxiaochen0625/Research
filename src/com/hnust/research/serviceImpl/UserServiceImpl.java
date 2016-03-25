package com.hnust.research.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnust.research.base.DaoSupportImpl;
import com.hnust.research.domain.User;
import com.hnust.research.service.UserService;

//对Service的具体实现类放到spring容器中初始化
@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService{

	/**
	 * 下面为新增的方法
	 */
	
	/**
	 * 验证用户是否存在
	 */
	@Override
	public User findByUserName(String username) {
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.username=?")//
				.setParameter(0, username)//
				.uniqueResult();
	}

	/**
	 * 根据用户名和密码实现登录
	 */
	@Override
	public User findByUserNameAndPassword(String username, String password) {
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.username=? AND u.password=?")//
				.setParameter(0, username)//
				.setParameter(1, password)//
				.uniqueResult();//
	}

	/**
	 * 查找昵称是否存在
	 */
	@Override
	public boolean findByNickName(String nickname) {
		User user=(User) getSession().createQuery(//
				"FROM User u WHERE u.nickname=?")//
				.setParameter(0, nickname)//
				.uniqueResult();
		if(user!=null){
			return true;
		}
		return false;
	}

	/**
	 * 通过id查找密码
	 */
	@Override
	public Object findPasswordById(Long id) {
		if(id==null){//如果id为空，则返回一个null类型的字符串
			return null;
		}else{
			return (String) getSession().createQuery(//
					"SELECT user.password FROM User user WHERE user.id=?")//
					.setParameter(0, id)//
					.uniqueResult();
		}
	}

	@Override
	public boolean exitsMail(String mail) {
		String email=(String) getSession().createQuery(//
				"SELECT user.mail FROM User user WHERE user.mail=?")//
				.setParameter(0, mail)//
				.uniqueResult();
		if(email==null)
			return false;
		else
			return true;
	}

	@Override
	public User findByActiveCode(String activeCode) {
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.activeCode=?")//
				.setParameter(0, activeCode)//
				.uniqueResult();
	}

	@Override
	public User findByMail(String mail) {
		return  (User) getSession().createQuery(//
				"FROM User u WHERE u.mail=?")//
				.setParameter(0, mail)//
				.uniqueResult();
	}

	@Override
	public List getLatestUsers(int number) {
		if(number<0){
			number=0;
		}
		return getSession().createQuery(//
				"FROM User u ORDER BY u.createdate DESC")//
				.setFirstResult(0)//
				.setMaxResults(number)//
				.list();
	}

	@Override
	public List getMostUploadUsers(int number) {
		if(number<0){
			number=0;
		}
		return getSession().createQuery(//
				"FROM User u ORDER BY u.sharecount DESC")
				.setFirstResult(0)
				.setMaxResults(number)
				.list();
	}
	
	
	
}
