package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import com.ssp.closet.dto.Bid;

public interface BidMapper {
	
	void createBid(Bid bid);

	void updatePrice(Bid bid);

	void deleteBid(int bidId);
	  
	void updateSuccessResult(int productId);
	  
	void updateFailResult(int productId);
	  
	int findMaxPrice(int productId);		 
	  
	List<Bid> getBidResultList(String userId);
	  
}
