package com.ssp.closet.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.ssp.closet.dto.Order;

public interface OrderDao {
	
	//주문 등록
	void insertOrder(Order order) throws DataAccessException;
	
	//내가 판매한 상품 리스트 조회
	List<Order> getSellList(String suppId) throws DataAccessException;
	
	//내가 구매한 상품 리스트 조회
	List<Order> getBuyList(String userId) throws DataAccessException;
}
