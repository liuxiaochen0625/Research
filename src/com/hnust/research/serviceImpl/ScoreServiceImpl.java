package com.hnust.research.serviceImpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnust.research.base.DaoSupportImpl;
import com.hnust.research.domain.Score;
import com.hnust.research.service.ScoreService;

@Service
@Transactional
public class ScoreServiceImpl extends DaoSupportImpl<Score> implements ScoreService{



}
