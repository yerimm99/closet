package com.ssp.closet.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ssp.closet.dao.MeetDao;

import com.ssp.closet.dto.Meet;

@Repository
public class JpaMeetDao implements MeetDao{
	@PersistenceContext
	private EntityManager em;
	
	public Meet getMeet(String userId, int productId) {
	    TypedQuery<Meet> query = em.createQuery(
	            "SELECT m FROM Meet m WHERE m.userId = :userId AND m.productId = :productId", Meet.class);
	    query.setParameter("userId", userId);
	    query.setParameter("productId", productId);
	    try {
	        return query.getSingleResult();
	    } catch (NoResultException ex) {
	        return null; // Meet가 없는 경우 null을 반환
	    }
	}
}
