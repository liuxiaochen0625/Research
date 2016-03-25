package com.hnust.research.service;

import com.hnust.research.base.DaoSupport;
import com.hnust.research.domain.Label;

public interface LabelService extends DaoSupport<Label>{

	Label getByType(String type);

}
