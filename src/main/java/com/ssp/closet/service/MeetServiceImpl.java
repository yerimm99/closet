package com.ssp.closet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssp.closet.dao.MeetDao;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Meet;


public class MeetServiceImpl implements MeetService {
	
	@Autowired
	private MeetDao meetDao;

	public int countPeopleNum(int productId) {
		return meetDao.countPeopleNum(productId);
	}

	public Meet getMeetDetail(int meetId) {
		return meetDao.getMeetDetail(meetId);
	}

	public List<Account> getMeetList(int productId) {
		return meetDao.getMeetList(productId);
	}
}
