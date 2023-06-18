package com.ssp.closet.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.ssp.closet.dto.Delivery;

public interface OrderDao {
	
	//주문 등록
	void insertOrder(Delivery order) throws DataAccessException;
	
	//내가 판매한 상품 리스트 조회
	List<Delivery> getSellList(String suppId) throws DataAccessException;
	
	//내가 구매한 상품 리스트 조회
	List<Delivery> getBuyList(String userId) throws DataAccessException;
	
	//주문 상세 조회
	Delivery getOrderDetail(int orderId) throws DataAccessException;
}
