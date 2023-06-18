package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssp.closet.dto.Delivery;

@Mapper
public interface OrderMapper {
	
	void insertOrder(Delivery order);
	List<Delivery> getSellList(String suppId);
	List<Delivery> getBuyList(String userId);
	Delivery getOrderDetail(int orderId);
}
