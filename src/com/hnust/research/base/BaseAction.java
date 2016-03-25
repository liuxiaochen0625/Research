package com.hnust.research.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.hnust.research.service.CommentService;
import com.hnust.research.service.CompetitionService;
import com.hnust.research.service.LabelService;
import com.hnust.research.service.NoticeService;
import com.hnust.research.service.ProgLanguageService;
import com.hnust.research.service.ScoreService;
import com.hnust.research.service.SoftService;
import com.hnust.research.service.SourceService;
import com.hnust.research.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 继承ActionSupport并且实现ModelDriven接口
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	//=====================ModelDriven的支持===================
	protected T model;
	
	public BaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取T，这个要继承
	 */
	@Override
	public T getModel() {
		return model;
	}
	
	
	//Action中要用到的Service实例的声明====================
	@Resource
	protected UserService userService;
	@Resource
	protected SourceService sourceService;
	@Resource
	protected CompetitionService competitionService;
	@Resource
	protected SoftService softService;
	@Resource
	protected LabelService labelService;
	@Resource
	protected ProgLanguageService progLanguageService;
	@Resource
	protected ScoreService scoreService;
	@Resource
	protected CommentService commentService;
	@Resource
	protected NoticeService noticeService;
	
	//===============分页用的信息========================
	protected int pageNum=1;  //当前页
	protected int pageSize=12;  //每页显示多少条记录

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
