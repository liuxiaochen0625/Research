package com.hnust.research.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnust.research.base.DaoSupportImpl;
import com.hnust.research.domain.PageBean;
import com.hnust.research.domain.Source;
import com.hnust.research.service.SourceService;
/**
 * 交给spring容器管理
 * 并且开事务
 * @author Administrator
 *
 */
@Service
@Transactional
public class SourceServiceImpl extends DaoSupportImpl<Source> implements SourceService{

	
	/**
	 * 	
	 * 		  first   max
		-------------------------------
		第1页           0      10
		第2页          10     10
		第3页          20     10
		first=(currentPage-1)*pageSize
		max=pageSize
	 */
	@Override
	@Deprecated
	public PageBean getPageBeanBySource(int pageNum, int pageSize) {
		//查询列表
		List list=getSession().createQuery(//
				"FROM Source ")//
				.setFirstResult((pageNum-1)*pageSize)//
				.setMaxResults(pageSize)//
				.list();
		//查询总数量
		Long count=(Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Source")//
				.uniqueResult();
		
		return new PageBean(pageNum,pageSize,list,count.intValue());
	}

	@Override
	public List getTop10() {
		return getSession().createQuery(//
				"FROM Source source WHERE source.status=1 ORDER BY source.count DESC")//
				.setFirstResult(0)//
				.setMaxResults(10)//
				.list();
	}

	@Override
	public List getLatest(int number) {
		if(number<0){
			number=0;
		}
		return getSession().createQuery(//
				"FROM Source source WHERE source.status=1 ORDER BY source.time DESC")//
				.setFirstResult(0)//
				.setMaxResults(number)//
				.list();//
	}

	@Override
	public List getRecommand(int number) {
		if(number<0){
			number=0;
		}
		return  getSession().createQuery(//
				"FROM Source source WHERE source.status=1 ORDER BY source.count ASC")//
				.setFirstResult(0)//
				.setMaxResults(number)//
				.list();//
	}


}
