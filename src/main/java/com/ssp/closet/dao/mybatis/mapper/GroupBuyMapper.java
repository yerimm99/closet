package com.ssp.closet.dao.mybatis.mapper;

import com.ssp.closet.dto.GroupBuy;

public interface GroupBuyMapper {
	int getPeopleNum(int productId);
	GroupBuy getGroupBuyDetail(int productId);
}
