package com.ssp.closet.dao.mybatis.mapper;

import com.ssp.closet.dto.Auction;

public interface AuctionMapper {
	
	void insertAuctionProduct(Auction auction);

	Auction getAuctionDetail(String productId);

}
