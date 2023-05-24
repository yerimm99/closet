package com.ssp.closet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ssp.closet.dto.Bid;

public interface BidDao {

	  void createBid(Bid bid) throws DataAccessException;

	  void updatePrice(Bid bid) throws DataAccessException;

	  void deleteBid(int bidId) throws DataAccessException;
	  
	  void updateSuccessResult(int productId) throws DataAccessException;
	  
	  void updateFailResult(int productId) throws DataAccessException;
	  
	  int findMaxPrice(int productId) throws DataAccessException;		 
	  
	  List<Bid> getBidResultList(String userId) throws DataAccessException;
	  
	 	
}
