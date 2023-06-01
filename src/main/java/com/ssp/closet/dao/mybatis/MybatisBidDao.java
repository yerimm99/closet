package com.ssp.closet.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dao.mybatis.mapper.BidMapper;
import com.ssp.closet.dto.Bid;

@Repository
public class MybatisBidDao {
	
	@Autowired
	private BidMapper bidMapper;

	public void createBid(Bid bid) throws DataAccessException {
		bidMapper.createBid(bid);
	};

	public void updatePrice(Bid bid) throws DataAccessException {
		  bidMapper.updatePrice(bid);
	};

	public void deleteBid(int bidId) throws DataAccessException {
		  bidMapper.deleteBid(bidId);
	};
	  
	public void updateSuccessResult(int productId) throws DataAccessException {
		  bidMapper.updateSuccessResult(productId);
	};
	  
	public void updateFailResult(int productId) throws DataAccessException {
		  bidMapper.updateFailResult(productId);
	};
	  
	public int findMaxPrice(int productId) throws DataAccessException {
		  return bidMapper.findMaxPrice(productId);
	};		 
	  
	public List<Bid> getBidResultList(String userId) throws DataAccessException {
		  return bidMapper.getBidResultList(userId);
	};
	  
}
