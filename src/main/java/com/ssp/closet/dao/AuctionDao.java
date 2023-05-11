package com.ssp.closet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ssp.closet.dto.Auction;

public interface AuctionDao {
	  void insertAuctionPrice(Auction auction) throws DataAccessException;

	  void updateAuctionPrice(Auction auction) throws DataAccessException;

	  int findMaxAuctionPrice(int productId) throws DataAccessException; // 파라미터 수정? 
	  
	  void updateSuccessAuctionResult(Auction auction) throws DataAccessException;
	  
	  void updateFailAuctionResult(Auction auction) throws DataAccessException;
		 
	  void deleteAuctionByUser(Auction auction) throws DataAccessException;
	  
	  List<Auction> getAuctionResultList(String userId) throws DataAccessException;
	  
	 	
	 

}
