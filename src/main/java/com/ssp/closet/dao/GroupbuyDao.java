package com.ssp.closet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ssp.closet.dto.Groupbuy;


public interface GroupbuyDao {

	void insertGroupbuy(Groupbuy groupbuy) throws DataAccessException;

	Groupbuy getGroupbuyDetail(int productId) throws DataAccessException;
	
	List<Groupbuy> getGroupbuyList() throws DataAccessException;
}
