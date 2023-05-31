package com.ssp.closet.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dao.mybatis.mapper.MeetMapper;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Meet;

@Repository
public class MybatisMeetDao {
	
	@Autowired
	private MeetMapper meetMapper;
	
	public void insertMeet(Meet meet) throws DataAccessException {
		meetMapper.insertMeet(meet);
	}
	
	public void deleteMeet(int meetId) throws DataAccessException {
		meetMapper.deleteMeet(meetId);
	}
	
	public int countPeopleNum(int productId) throws DataAccessException {
		return meetMapper.countPeopleNum(productId);
	}
	
	public Meet getMeetDetail(int meetId) throws DataAccessException {
		return meetMapper.getMeetDetail(meetId);
	}
	
	public List<Account> getMeetList(int productId) throws DataAccessException {
		return meetMapper.getMeetList(productId);
	}
}
