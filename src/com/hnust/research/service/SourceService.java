package com.hnust.research.service;
import java.util.List;

import com.hnust.research.base.DaoSupport;
import com.hnust.research.domain.PageBean;
import com.hnust.research.domain.Source;

public interface SourceService extends DaoSupport<Source>{

	/**
	 * 获得source的分页PageBean
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Deprecated
	PageBean getPageBeanBySource(int pageNum, int pageSize);
	
	/**
	 * 获得最多下载的10个资源
	 * @return
	 */
	List getTop10();
	
	/**
	 * 获得当前最新的number个资源
	 * @param number
	 * @return
	 */
	List getLatest(int number);
	
	/**
	 * 获得推荐下载的number个资源
	 * @param number
	 * @return
	 */
	List getRecommand(int number);

}
