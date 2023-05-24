package com.ssp.closet.dao;

import org.springframework.dao.DataAccessException;

import com.ssp.closet.dto.Auction;

public interface AuctionDao {
	
	  void createAuction(Auction auction) throws DataAccessException;

	  void deleteAuction(String productId) throws DataAccessException;

}
