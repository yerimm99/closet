package com.ssp.closet.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.ssp.closet.dao.AuctionDao;
import com.ssp.closet.dao.mybatis.mapper.AuctionMapper;
import com.ssp.closet.dto.Auction;

public class MybatisAuctionDao implements AuctionDao{
	
	@Autowired
	private AuctionMapper auctionMapper;
	
	public void insertAuction(Auction auction) throws DataAccessException {
		auctionMapper.insertAuction(auction);
	};

	public Auction getAuctionDetail(String productId) throws DataAccessException {
		return auctionMapper.getAuctionDetail(productId);
	};


}
