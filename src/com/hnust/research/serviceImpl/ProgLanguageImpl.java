package com.hnust.research.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnust.research.base.DaoSupportImpl;
import com.hnust.research.domain.ProgLanguage;
import com.hnust.research.service.ProgLanguageService;

@Service
@Transactional
public class ProgLanguageImpl extends DaoSupportImpl<ProgLanguage> implements ProgLanguageService{

	public ProgLanguage getByLanguage(String language) {
		return (ProgLanguage) getSession().createQuery(//
				"FROM ProgLanguage progLanguage WHERE progLanguage.language=?")
				.setParameter(0, language)
				.uniqueResult();
	}

}
