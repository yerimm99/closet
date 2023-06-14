package com.ssp.closet.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssp.closet.dto.Groupbuy;

@Mapper
public interface GroupbuyMapper {
	void insertGroupbuy(Groupbuy groupbuy);

	Groupbuy getGroupbuyDetail(int productId);
}
