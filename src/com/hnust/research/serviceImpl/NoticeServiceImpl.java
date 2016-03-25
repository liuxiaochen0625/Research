package com.hnust.research.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnust.research.base.DaoSupportImpl;
import com.hnust.research.domain.Notice;
import com.hnust.research.service.NoticeService;

@Transactional
@Service
public class NoticeServiceImpl extends DaoSupportImpl<Notice> implements NoticeService{

	@Override
	public List<Notice> getList(int i) {
		return getSession().createQuery(//
				"FROM Notice notice ORDER BY notice.datetime DESC")//
				.setFirstResult(0)//
				.setMaxResults(i)//
				.list();
	}

}
