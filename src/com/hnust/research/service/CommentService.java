package com.hnust.research.service;

import java.util.List;

import com.hnust.research.base.DaoSupport;
import com.hnust.research.domain.Comment;
import com.hnust.research.domain.Soft;
import com.hnust.research.domain.Source;

public interface CommentService extends DaoSupport<Comment>{

	List<Comment> getBy(Soft soft);
	List<Comment> getBy(Source source);

}
