package com.ssp.closet.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dao.SequenceDao;
import com.ssp.closet.dto.Groupbuy;

@Repository
public class JpaGroupbuyDao {
	@PersistenceContext
    private EntityManager em;
	@Autowired
	private SequenceDao sequenceDao;
	
	public void insertGroupbuy(Groupbuy Groupbuy) throws DataAccessException {
		int newProductId = sequenceDao.getNextId("productId");
    	Groupbuy.setProductId(newProductId);
		em.persist(Groupbuy);
	};

	public Groupbuy getGroupbuyDetail(int productId) throws DataAccessException {
		return em.find(Groupbuy.class, productId);
	};
}
