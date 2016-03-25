package com.hnust.research.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnust.research.base.DaoSupportImpl;
import com.hnust.research.domain.Label;
import com.hnust.research.service.LabelService;

@Service
@Transactional
public class LabelServiceImpl extends DaoSupportImpl<Label> implements LabelService{

	@Override
	public Label getByType(String type) {
		return (Label) getSession().createQuery(//
				"FROM Label label WHERE label.type=?")//
				.setParameter(0, type)//
				.uniqueResult();
	}

}
