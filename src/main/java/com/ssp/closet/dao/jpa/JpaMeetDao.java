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
}
