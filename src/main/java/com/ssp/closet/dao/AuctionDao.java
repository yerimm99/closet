package com.ssp.closet.dao;

import org.springframework.dao.DataAccessException;

import com.ssp.closet.dto.Auction;

public interface AuctionDao {
	
	void insertAuction(Auction auction) throws DataAccessException;

	Auction getAuctionDetail(int productId) throws DataAccessException;

}
