package com.hnust.research.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnust.research.base.DaoSupportImpl;
import com.hnust.research.domain.Competition;
import com.hnust.research.service.CompetitionService;

@Service
@Transactional
public class CompetitionServiceImpl extends DaoSupportImpl<Competition> implements CompetitionService {

	@Override
	public List<Competition> getList(int i) {
		return getSession().createQuery(//
				"FROM Competition competition where competition.status=? ORDER BY competition.start DESC")//
				.setParameter(0, "正在进行")//
				.setFirstResult(0)//
				.setMaxResults(i)//
				.list();
	}

}
