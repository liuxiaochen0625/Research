package com.hnust.research.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hnust.research.base.BaseAction;
import com.hnust.research.domain.Comment;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Comment>{

}
