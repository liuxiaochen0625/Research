package com.hnust.research.util;

import java.util.ArrayList;
import java.util.List;

import com.hnust.research.base.DaoSupport;
import com.hnust.research.domain.PageBean;
import com.opensymphony.xwork2.ActionContext;

/**
 * 分页的hql语句辅助类
 * @author Administrator
 *
 */

public class QueryHelper {
	private String fromClause; //FROM子句
	private String whereClause=""; //WHERE子句
	private String orderByClause=""; //OrderBy
	
	private List<Object> parameters=new ArrayList<Object>();
	/**
	 * 生成from子句
	 * @param clazz
	 * @param alias 别名
	 */
	public QueryHelper(Class clazz,String alias){
		fromClause="FROM "+clazz.getSimpleName()+" "+alias;
	}
	
	/**
	 * 拼接条件语句
	 * @param condition
	 * @param params
	 * @return
	 */
	public QueryHelper addCondition(String condition,Object...params){
		//拼接
		if(whereClause.length()==0){
			whereClause=" WHERE "+condition;			
		}else{
			whereClause+=" AND "+condition;
		}
		
		//参数
		if(params!=null){
			for(Object p:params){
				parameters.add(p);
			}
		}
		return this;
	}
	
	/**
	 * 对上面方法的重载,增加一个判断条件
	 * @param append
	 * @param condition
	 * @param params
	 * @return
	 */
	
	public QueryHelper addCondition(boolean append,String condition,Object...params){
		if(append){
				addCondition(condition,params);
		}
		return this;
	}
	
	/**
	 * 专门为模糊查询准备的
	 * @param append
	 * @param condition
	 * @return
	 */
	public QueryHelper addCondition(boolean append,String condition){
		if(append){
			if(whereClause.length()==0){
				whereClause=" WHERE "+condition;
			}else{
				whereClause=" AND "+condition;
			}
		}
		return this;
	}
	
	
	/**
	 * 拼接OrderBy子句
	 * @param propertyName
	 * 				参与排序的属性名
	 * @param asc
	 * 				0表示升序,1表示降序
	 */
	public QueryHelper addOrderProperty(String propertyName,String asc){
		if(orderByClause.length()==0){
			orderByClause=" ORDER BY "+propertyName+(asc.equals("ASC")? " ASC":" DESC");
		}else{
			orderByClause+=", "+propertyName+(asc.equals("ASC")?" ASC":" DESC");
		}
		return this;
	}
	
	/**
	 * 如果满足append这个条件，才执行添加
	 * @param append
	 * @param propertyName
	 * @param asc
	 * @return
	 */
	public QueryHelper addOrderProperty(boolean append,String propertyName,String asc){
		if(append){
			addOrderProperty(propertyName, asc);			
		}
		return this;
	}
	
	
	
	
	/**
	 * 获取生成的用于查询数据列表的HQL语句
	 * @return
	 */
	public String getListQueryHql(){
		return fromClause+whereClause+orderByClause;
	}
	
	/**
	 * 获取生成的用于查询总记录数的HQL语句
	 * @return
	 */
	public String getCountQueryHql(){
		return "SELECT COUNT(*) "+fromClause+whereClause;
	}

	/**
	 * 获取HQL中的参数值列表
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}
	
	/**
	 * 准备数据到值栈顶
	 * @param service
	 * @param pageNum
	 * @param pageSize
	 */
	public void preparePageBean(DaoSupport<?> service,int pageNum,int pageSize){
		PageBean pageBean=service.getPageBean(pageNum, pageSize,this);
		ActionContext.getContext().getValueStack().push(pageBean);
	}
	
}
