package com.ssp.closet.dao.mybatis.mapper;

import com.ssp.closet.dto.Auction;

public interface AuctionMapper {
	
	void createAuction(Auction auction);

	void deleteAuction(String productId);

}
