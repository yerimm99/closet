package com.ssp.closet.dao;

import org.springframework.dao.DataAccessException;

import com.ssp.closet.dto.Auction;

public interface AuctionDao {
	
	void insertAuctionProduct(Auction auction) throws DataAccessException;

	Auction getAuctionDetail(String productId) throws DataAccessException;

}
