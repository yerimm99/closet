package com.ssp.closet.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssp.closet.dto.Auction;

@Mapper
public interface AuctionMapper {
	
	void insertAuction(Auction auction);

	Auction getAuctionDetail(int productId);
	
	void updateMaxPrice(Auction auction);

}
