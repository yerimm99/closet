package com.ssp.closet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.closet.dao.OrderDao;
import com.ssp.closet.dto.Order;

@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	public List<Order> getSellList(String suppId) {
		return orderDao.getSellList(suppId);
	}

	public List<Order> getBuyList(String userId) {
		return orderDao.getBuyList(userId);
	}

	public Order getOrderDetail(int orderId) {
		return orderDao.getOrderDetail(orderId);
	}
}
