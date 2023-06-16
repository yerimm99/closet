package com.ssp.closet.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dao.GroupbuyDao;
import com.ssp.closet.dao.SequenceDao;
import com.ssp.closet.dto.Groupbuy;

@Repository
public class JpaGroupbuyDao implements GroupbuyDao{
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

	public List<Groupbuy> getGroupbuyList() throws DataAccessException {
		TypedQuery<Groupbuy> query = em.createQuery("SELECT g FROM Groupbuy g", Groupbuy.class);
		return query.getResultList();
	}
}
