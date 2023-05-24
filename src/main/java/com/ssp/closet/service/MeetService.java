package com.ssp.closet.service;

import java.util.List;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Meet;

public interface MeetService {
	
	int countPeopleNum(int productId);
	Meet getMeetDetail(int meetId);
	List<Account> getMeetList(int productId);
}
