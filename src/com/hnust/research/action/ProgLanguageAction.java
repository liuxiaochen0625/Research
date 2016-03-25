package com.hnust.research.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hnust.research.base.BaseAction;
import com.hnust.research.domain.Label;
import com.hnust.research.domain.ProgLanguage;
import com.hnust.research.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class ProgLanguageAction extends BaseAction<ProgLanguage>{
	
	private String result;
	
	public String list() throws Exception{
		QueryHelper queryHelper=new QueryHelper(ProgLanguage.class, "ProgLanguage");
		queryHelper.preparePageBean(progLanguageService, pageNum, pageSize);
		return "list";
	}
	
	public String addUI() throws Exception{
		return "saveUI";
	}
	
	public String add() throws Exception{
		if(model.getLanguage()!=null){
			progLanguageService.save(model);
			return "toList";
		}else{
			return null;
		}
	}

	public String editUI() throws Exception{
		ProgLanguage progLanguage=progLanguageService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(progLanguage);
		return "saveUI";
	}
	
	public String edit() throws Exception{
		ProgLanguage progLanguage=progLanguageService.getById(model.getId());
		progLanguage.setLanguage(model.getLanguage());
		progLanguageService.update(progLanguage);
		return "toList";
	}
	
	public String delete() throws Exception{
		if(model.getId()!=null){
			progLanguageService.delete(model.getId());
			return "toList";
		}else{
			return null;
		}
	}
	
	public String exists() throws Exception{
		ProgLanguage progLanguage=progLanguageService.getByLanguage(model.getLanguage());
		if(progLanguage!=null){
			result="{'exists':'true'}";
		}else{
			result="{'exists':'false'}";
		}
		return "exists";
	}
	
	public String exists1() throws Exception{
		ProgLanguage progLanguage=progLanguageService.getById(model.getId());
		ProgLanguage progLanguage2=progLanguageService.getByLanguage(model.getLanguage());
		if(progLanguage2!=null){//说明存在一个这样的结果
			if(!progLanguage2.getLanguage().equals(progLanguage.getLanguage())){//如果不是本身的话,不可以进行更新
				result="{'exists':'true'}";
			}else{//可以更新
				result="{'exists':'false'}";
			}
		}
		return "exists";
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}
