package com.ssp.closet.service;

import java.util.List;

import com.ssp.closet.dto.Delivery;

public interface OrderService {
	
	List<Delivery> getSellList(String suppId);
	List<Delivery> getBuyList(String userId);
	Delivery getOrderDetail(int orderId);
}
