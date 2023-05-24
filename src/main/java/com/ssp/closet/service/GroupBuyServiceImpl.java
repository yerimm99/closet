package com.ssp.closet.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssp.closet.dao.GroupBuyDao;
import com.ssp.closet.dto.GroupBuy;

public class GroupBuyServiceImpl implements GroupBuyService{

	@Autowired
	private GroupBuyDao groupBuyDao;
	
	public int getPeopleNum(int productId) {
		return groupBuyDao.getPeopleNum(productId);
	}

	public GroupBuy getGroupBuyDetail(int productId) {
		return groupBuyDao.getGroupBuyDetail(productId);
	}
}
