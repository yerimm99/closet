package com.ssp.closet.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssp.closet.dto.GroupBuy;
@Mapper
public interface GroupBuyMapper {
	int getPeopleNum(int productId);
	GroupBuy getGroupBuyDetail(int productId);
}
