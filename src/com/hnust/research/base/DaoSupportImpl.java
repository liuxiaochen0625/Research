package com.hnust.research.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.hnust.research.domain.PageBean;
import com.hnust.research.util.QueryHelper;

@Transactional
@SuppressWarnings("unchecked")
public class DaoSupportImpl<T> implements DaoSupport<T> {
	
	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;
	
	public DaoSupportImpl(){
		/**
		 * 通过反射技术来获取真实类型
		 */
		//获取当前new的对象的泛型的父类类型
		ParameterizedType pt=(ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz=(Class<T>) pt.getActualTypeArguments()[0];
		System.out.println("clazz----->"+clazz);
	}

	/**
	 * 获取当前可用的Session
	 * @return
	 */
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 保存实体
	 */
	public void save(T entity) {
		getSession().save(entity);
	}

	/**
	 * 删除实体
	 */
	public void delete(Long id) {
		Object obj=getById(id);
		if(obj!=null){
			getSession().delete(obj);
		}
	}
	
	/**
	 * 删除实体
	 */
	public void delete(Integer id) {
		Object obj=getById(id);
		if(obj!=null){
			getSession().delete(obj);
		}
	}
	

	/**
	 * 更新实体
	 */
	public void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * 获得实体
	 */
	public T getById(Long id) {
		if(id==null){
			return null;
		}else{
			return (T) getSession().get(clazz, id);
		}
	}
	
	public T getById(Integer id) {
		if(id==null){
			return null;
		}else{
			return (T) getSession().get(clazz, id);
		}
	}
	

	/**
	 * 查找所有实体
	 */
	public List<T> findAll() {
		return getSession().createQuery(//
				"FROM "+clazz.getSimpleName())//
				.list();
	}

	/**
	 * 公共的查询分页信息的方法
	 */
	@Override
	@Deprecated
	public PageBean getPageBean(int pageNum, int pageSize, String hql,List<Object> parameters) {
		System.out.println("----------->DaoSupportImpl.getPageBean()");
		//查询本页的数据列表
		Query listQuery=getSession().createQuery(hql);
		if(parameters!=null){//设置参数
			for(int i=0;i<parameters.size();i++){
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		listQuery.setFirstResult((pageNum-1)*pageSize);
		listQuery.setMaxResults(pageSize);
		List list=listQuery.list();//执行查询
		
		//查询总记录数量
		Query countQuery=getSession().createQuery("SELECT COUNT(*)"+hql);
		if(parameters!=null){//设置参数
			for(int i=0;i<parameters.size();i++){
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long count=(Long) countQuery.uniqueResult();//执行查询
		
		return new PageBean(pageNum,pageSize,list,count.intValue());
	}

	/**
	 * 公共的查询分页的方法实现（最终版）
	 */
	@Override
	public PageBean getPageBean(int pageNum, int pageSize,QueryHelper queryHelper) {
		System.out.println("----------->DaoSupportImpl.getPageBean(int pageNum, int pageSize,QueryHelper queryHelper)------>");
		
		List<Object> parameters=queryHelper.getParameters();
		//查询本页的数据列表
		System.out.println(queryHelper.getListQueryHql());
		System.out.println(parameters.size());
		Query listQuery=getSession().createQuery(queryHelper.getListQueryHql());
		if(parameters!=null){//设置参数
			for(int i=0;i<parameters.size();i++){
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		listQuery.setFirstResult((pageNum-1)*pageSize);
		listQuery.setMaxResults(pageSize);
		List list=listQuery.list();//执行查询
		
		//查询总记录数量
		Query countQuery=getSession().createQuery(queryHelper.getCountQueryHql());
		if(parameters!=null){//设置参数
			for(int i=0;i<parameters.size();i++){
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long count=(Long) countQuery.uniqueResult();//执行查询
		
		return new PageBean(pageNum,pageSize,list,count.intValue());
	}

}
