package com.hnust.research.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hnust.research.base.BaseAction;
import com.hnust.research.domain.Label;
import com.hnust.research.domain.ProgLanguage;
import com.hnust.research.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class LabelAction extends BaseAction<Label>{
	
	private String result;
	
	public String list() throws Exception{
		QueryHelper queryHelper=new QueryHelper(Label.class, "Label");
		queryHelper.preparePageBean(labelService, pageNum, pageSize);
		return "list";
	}
	
	public String addUI() throws Exception{
		return "saveUI";
	}
	
	public String add() throws Exception{
		if(model.getType()!=null){
			labelService.save(model);
			return "toList";
		}else{
			return null;
		}
	}

	public String editUI() throws Exception{
		Label label=labelService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(label);
		return "saveUI";
	}
	
	public String edit() throws Exception{
		Label label=labelService.getById(model.getId());
		label.setType(model.getType());
		labelService.update(label);
		return "toList";
	}
	
	public String delete() throws Exception{
		if(model.getId()!=null){
			labelService.delete(model.getId());
			return "toList";
		}else{
			return null;
		}
	}
	
	public String exists() throws Exception{
		Label label=labelService.getByType(model.getType());
		if(label!=null){
			result="{'exists':'true'}";
		}else{
			result="{'exists':'false'}";
		}
		return "exists";
	}
	
	public String exists1() throws Exception{
		Label label=labelService.getById(model.getId());
		Label label2=labelService.getByType(model.getType());
		if(label2!=null){
			if(!label2.getType().equals(label.getType())){//如果不等于自身,不可以提交
				result="{'exists':'true'}";
			}else{//可以提交
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
