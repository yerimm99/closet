package com.ssp.closet.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dao.SequenceDao;
import com.ssp.closet.dao.AuctionDao;
import com.ssp.closet.dto.Auction;

@Repository
public class JpaAuctionDao implements AuctionDao{
	
	@PersistenceContext
    private EntityManager em;
	@Autowired
	private SequenceDao sequenceDao;
	
	public void insertAuction(Auction auction) throws DataAccessException {
		int newProductId = sequenceDao.getNextId("a");
    	auction.setProductId(newProductId);
		em.persist(auction);
	}

	public Auction getAuctionDetail(int productId) throws DataAccessException {
		return em.find(Auction.class, productId);
	}
}
