package com.ssp.closet.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.ssp.closet.dao.AuctionDao;
import com.ssp.closet.dao.mybatis.mapper.AuctionMapper;
import com.ssp.closet.dto.Auction;

public class MybatisAuctionDao implements AuctionDao{
	
	@Autowired
	private AuctionMapper auctionMapper;
	
	public void insertAuctionPrice(Auction auction) throws DataAccessException {
		auctionMapper.insertAuctionPrice(auction);
	}

	public void updateAuctionPrice(Auction auction) throws DataAccessException{
		auctionMapper.updateAuctionPrice(auction);
		
	}

	public int findMaxAuctionPrice(int productId) throws DataAccessException{
		return auctionMapper.findMaxAuctionPrice(productId);
		
	}
	  
	public void updateSuccessAuctionResult(Auction auction) throws DataAccessException{
		auctionMapper.updateSuccessAuctionResult(auction);
	}
	
	public void updateFailAuctionResult(Auction auction) throws DataAccessException{
		auctionMapper.updateFailAuctionResult(auction);
	}
	
	public void deleteAuctionByUser(Auction auction) throws DataAccessException{
			auctionMapper.deleteAuctionByUser(auction);
	}
	
	public List<Auction> getAuctionResultList(String userId) throws DataAccessException{
		return auctionMapper.getAuctionResultList(userId);
	}

}
