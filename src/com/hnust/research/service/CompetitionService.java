package com.hnust.research.service;

import java.util.List;

import com.hnust.research.base.DaoSupport;
import com.hnust.research.domain.Competition;

/**
 * 竞赛类Service层
 * @author Administrator
 *
 */
public interface CompetitionService extends DaoSupport<Competition>{

	List<Competition> getList(int i);

}
