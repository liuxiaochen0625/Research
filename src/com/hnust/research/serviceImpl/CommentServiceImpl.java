package com.hnust.research.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnust.research.base.DaoSupportImpl;
import com.hnust.research.domain.Comment;
import com.hnust.research.domain.Soft;
import com.hnust.research.domain.Source;
import com.hnust.research.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl extends DaoSupportImpl<Comment> implements CommentService{

	@Override
	public List<Comment> getBy(Soft soft) {
		String flag=soft.getClass().getSimpleName().toLowerCase();
		return getSession().createQuery(//
				"FROM Comment c WHERE c.flag=? and c.toId=?")//
				.setParameter(0, flag)//
				.setParameter(1, soft.getId())
				.list();
	}

	@Override
	public List<Comment> getBy(Source source) {
		String flag=source.getClass().getSimpleName().toLowerCase();
		return getSession().createQuery(//
				"FROM Comment c WHERE c.flag=? and c.toId=?")//
				.setParameter(0, flag)//
				.setParameter(1, source.getId())
				.list();
	}

}
