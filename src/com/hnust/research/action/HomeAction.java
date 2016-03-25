package com.hnust.research.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hnust.research.base.BaseAction;
import com.hnust.research.domain.Competition;
import com.hnust.research.domain.Label;
import com.hnust.research.domain.Notice;
import com.hnust.research.domain.ProgLanguage;
import com.hnust.research.domain.Source;
import com.hnust.research.domain.User;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class HomeAction extends BaseAction<User>{
	//返回主页
	public String index() throws Exception{
		/**
		 * 准备数据
		 */
		List<Label> labelList=new ArrayList<Label>();
		List<ProgLanguage> languageList=new ArrayList<ProgLanguage>();
		List<Source> top10List=new ArrayList<Source>();
		List<Source> latestShareList=new ArrayList<Source>();
		List<Source> recommendList=new ArrayList<Source>();
		List<User> latestUsersList=new ArrayList<User>();
		List<User> mostUploadUsersList=new ArrayList<User>();
		List<Notice> noticeList=new ArrayList<Notice>();
		List<Competition> competitionList=new ArrayList<Competition>();
		
		labelList=labelService.findAll();
		languageList=progLanguageService.findAll();
		top10List=sourceService.getTop10();
		latestShareList=sourceService.getLatest(6);//获取最新前六
		recommendList=sourceService.getRecommand(6);//获取前六
		latestUsersList=userService.getLatestUsers(6);//获得最近6个加入者
		mostUploadUsersList=userService.getMostUploadUsers(6); //获得最多上传前六位
		noticeList=noticeService.getList(5);//获得最近5条公告
		competitionList=competitionService.getList(5); //获得最近五个比赛
		
		
		ActionContext.getContext().put("labelList", labelList);
		ActionContext.getContext().put("languageList", languageList);
		ActionContext.getContext().put("top10List", top10List);
		ActionContext.getContext().put("latestShareList", latestShareList);
		ActionContext.getContext().put("recommendList", recommendList);
		ActionContext.getContext().put("latestUsersList", latestUsersList);
		ActionContext.getContext().put("mostUploadUsersList", mostUploadUsersList);
		ActionContext.getContext().put("noticeList", noticeList);
		ActionContext.getContext().put("competitionList", competitionList);
		return "index";
	}
}
