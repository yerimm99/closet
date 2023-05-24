package com.ssp.closet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Meet;

public interface MeetDao {

	//공동구매 신청 등록
	void insertMeet(Meet meet) throws DataAccessException;

	//공동구매 신청 삭제
	void deleteMeet(int meetId) throws DataAccessException;

	//현재 공동구매 참여자 인원 계산
	int countPeopleNum(int productId) throws DataAccessException;

	//공동구매 신청 정보 상세보기
	Meet getMeetDetail(int meetId) throws DataAccessException;

	//공동구매 참여자 리스트 조회
	List<Account> getMeetList(int productId) throws DataAccessException;
}
