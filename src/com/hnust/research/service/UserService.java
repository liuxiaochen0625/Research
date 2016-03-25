package com.hnust.research.service;

import java.util.List;

import com.hnust.research.base.DaoSupport;
import com.hnust.research.domain.User;

public interface UserService extends DaoSupport<User>{
	/**
	 * 继承了最基本的五个方法，以下是另外特有的方法
	 */
	
	
	/**
	 * 通过名字查找用户
	 * @param username 用户名
	 * @return
	 */
	User findByUserName(String username);

	/**
	 * 通过用户名和密码实现登录用户功能
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	User findByUserNameAndPassword(String username, String password);

	/**
	 * 查找昵称是否存在
	 * @param nickname
	 * @return
	 */
	boolean findByNickName(String nickname);

	/**
	 * 通过id查找用户名的密码
	 * @param id
	 * @return
	 */
	Object findPasswordById(Long id);

	/**
	 * 邮箱是否被注册 
	 * @param mail
	 * @return
	 */
	boolean exitsMail(String mail);

	/**
	 * 通过activeCode找到用户,并且激活,activeCode在数据库中不可能重复
	 * @param activeCode
	 * @return
	 */
	User findByActiveCode(String activeCode);

	/**
	 * 通过mail找用户
	 * @param mail
	 * @return
	 */
	User findByMail(String mail);
	
	/**
	 * 获得最近加入的number个用户
	 * @param number
	 * @return
	 */
	List getLatestUsers(int number);
	
	List getMostUploadUsers(int number);
	
}
