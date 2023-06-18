package com.ssp.closet.dao.mybatis;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dao.OrderDao;
import com.ssp.closet.dao.SequenceDao;
import com.ssp.closet.dao.mybatis.mapper.OrderMapper;
import com.ssp.closet.dto.Delivery;

@Repository
public class MybatisOrderDao implements OrderDao {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private SequenceDao sequenceDao;

	//@Transactional
	public void insertOrder(Delivery order) throws DataAccessException {
		order.setOrderId(sequenceDao.getNextId("ordernum")); //ordernum 은 추후 수정
    	orderMapper.insertOrder(order);
	}

	public List<Delivery> getSellList(String suppId) throws DataAccessException {
		return orderMapper.getSellList(suppId);
	}

	public List<Delivery> getBuyList(String userId) throws DataAccessException {
		return orderMapper.getBuyList(userId);
	}

	public Delivery getOrderDetail(int orderId) throws DataAccessException {
		return orderMapper.getOrderDetail(orderId);
	}
}
