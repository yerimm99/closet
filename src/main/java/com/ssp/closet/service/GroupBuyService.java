package com.ssp.closet.service;

import com.ssp.closet.dto.GroupBuy;

public interface GroupBuyService {
	int getPeopleNum(int productId);
	GroupBuy getGroupBuyDetail(int productId);
}
