package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssp.closet.dto.Order;

@Mapper
public interface OrderMapper {
	
	void insertOrder(Order order);
	List<Order> getSellList(String suppId);
	List<Order> getBuyList(String userId);
	Order getOrderDetail(int orderId);
}
