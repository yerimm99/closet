package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;
import com.ssp.closet.dto.Order;

public interface OrderMapper {
	void insertOrder(Order order);
	
	List<Order> getSellList(String suppId);
	
	List<Order> getBuyList(String userId);
}
