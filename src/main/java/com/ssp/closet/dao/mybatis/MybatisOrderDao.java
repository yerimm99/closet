package com.ssp.closet.dao.mybatis;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ssp.closet.dao.OrderDao;
import com.ssp.closet.dao.mybatis.mapper.OrderMapper;
import com.ssp.closet.dto.Order;

public class MybatisOrderDao implements OrderDao {
	
	private OrderMapper orderMapper;
	
	@Override
	public void insertOrder(Order order) throws DataAccessException {
		orderMapper.insertOrder(order);
	}

	@Override
	public List<Order> getSellList(String suppId) throws DataAccessException {
		List<Order> list = orderMapper.getSellList(suppId);
		return list;
	}

	@Override
	public List<Order> getBuyList(String userId) throws DataAccessException {
		List<Order> list = orderMapper.getBuyList(userId);
		return list;
	}
}
