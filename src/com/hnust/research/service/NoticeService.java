package com.hnust.research.service;

import java.util.List;

import com.hnust.research.base.DaoSupport;
import com.hnust.research.domain.Notice;

public interface NoticeService extends DaoSupport<Notice>{

	List<Notice> getList(int i);

}
