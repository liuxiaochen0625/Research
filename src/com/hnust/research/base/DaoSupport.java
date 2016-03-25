package com.hnust.research.base;

import java.util.List;

import com.hnust.research.domain.PageBean;
import com.hnust.research.util.QueryHelper;

public interface DaoSupport<T> {
	/**
	 * 保存实体
	 * @param entity
	 */
	void save(T entity);
	
	/**
	 * 删除实体
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 删除实体
	 * @param id
	 */
	void delete(Integer id);
	
	
	/**
	 * 更新实体
	 * @param entity
	 */
	void update(T entity);
	
	/**
	 * 根据id查询实体
	 * @param id
	 * @return
	 */
	T getById(Long id);
	
	/**
	 * 根据id查询实体
	 * @param id
	 * @return
	 */
	T getById(Integer id);
	
	/**
	 * 查询所有
	 * @return
	 */
	List<T> findAll();
	
	
	/**
	 * 公共的查询分页的方法
	 * @param pageNum
	 * @param pageSize
	 * @param hql
	 * 			查询数据列表的HQL
	 * @param parameters
	 * 			参数列表,与HQL中问号一一对应,比如按照时间,作者等等排序
	 * @return
	 */
	@Deprecated
	PageBean getPageBean(int pageNum,int pageSize,String hql,List<Object> parameters);
	
	/**
	 * 公共的查询分页的方法（最终版）
	 * @param pageNum
	 * @param pageSize
	 * @param queryHelper
	 * @return
	 */
	PageBean getPageBean(int pageNum,int pageSize,QueryHelper queryHelper);
}
