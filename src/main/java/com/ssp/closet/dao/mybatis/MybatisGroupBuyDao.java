package com.ssp.closet.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dao.GroupBuyDao;
import com.ssp.closet.dao.mybatis.mapper.GroupBuyMapper;
import com.ssp.closet.dto.GroupBuy;

@Repository
public class MybatisGroupBuyDao implements GroupBuyDao {

	@Autowired
	private GroupBuyMapper groupBuyMapper;
	
	public int getPeopleNum(int productId) throws DataAccessException {
		return groupBuyMapper.getPeopleNum(productId);
	}

	public GroupBuy getGroupBuyDetail(int productId) throws DataAccessException {
		return groupBuyMapper.getGroupBuyDetail(productId);
	}
}
