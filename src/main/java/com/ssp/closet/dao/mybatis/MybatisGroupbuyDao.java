package com.ssp.closet.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dao.GroupbuyDao;
import com.ssp.closet.dao.mybatis.mapper.GroupbuyMapper;
import com.ssp.closet.dto.Groupbuy;

@Repository
public class MybatisGroupbuyDao implements GroupbuyDao {

	@Autowired
	private GroupbuyMapper groupbuyMapper;

	public void insertGroupbuy(Groupbuy insertGroupbuy) throws DataAccessException {
		groupbuyMapper.insertGroupbuy(insertGroupbuy);
	}

	public Groupbuy getGroupbuyDetail(int productId) throws DataAccessException {
		return groupbuyMapper.getGroupbuyDetail(productId);
	}
}
