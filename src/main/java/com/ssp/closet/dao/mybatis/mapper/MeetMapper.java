package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Meet;

public interface MeetMapper {
	void insertMeet(Meet meet);
	void deleteMeet(int meetId);
	int countPeopleNum(int productId);
	Meet getMeetDetail(int meetId);
	List<Account> getMeetList(int productId);
}
