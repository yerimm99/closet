package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Meet;

@Mapper
public interface MeetMapper {
	void insertMeet(Meet meet);
	void deleteMeet(int meetId);
	int countPeopleNum(int productId);
	Meet getMeetDetail(int meetId);
	List<Account> getMeetList(int productId);
}
