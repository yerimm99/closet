package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssp.closet.dto.Groupbuy;

@Mapper
public interface GroupbuyMapper {
	void insertGroupbuy(Groupbuy groupbuy);

	Groupbuy getGroupbuyDetail(int productId);
	
	List<Groupbuy> getGroupbuyList();
}
