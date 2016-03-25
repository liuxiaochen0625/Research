package com.hnust.research.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnust.research.base.DaoSupportImpl;
import com.hnust.research.domain.Soft;
import com.hnust.research.service.SoftService;

@Service
@Transactional
public class SoftServiceImpl extends DaoSupportImpl<Soft> implements SoftService{

}
