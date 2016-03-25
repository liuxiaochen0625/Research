package com.hnust.research.service;

import com.hnust.research.base.DaoSupport;
import com.hnust.research.domain.ProgLanguage;

public interface ProgLanguageService extends DaoSupport<ProgLanguage>{

	/**
	 * 通过语言获得这个对象
	 * @param language
	 * @return
	 */
	ProgLanguage getByLanguage(String language);

}
