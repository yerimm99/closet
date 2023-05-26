package com.ssp.closet.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssp.closet.dto.Auction;

@Mapper
public interface AuctionMapper {
	
	void insertAuctionProduct(Auction auction);

	Auction getAuctionDetail(String productId);

}
