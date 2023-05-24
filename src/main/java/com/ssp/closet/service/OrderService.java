package com.ssp.closet.service;

import java.util.List;

import com.ssp.closet.dto.Order;

public interface OrderService {
	
	List<Order> getSellList(String suppId);
	List<Order> getBuyList(String userId);
	Order getOrderDetail(int orderId);
}
