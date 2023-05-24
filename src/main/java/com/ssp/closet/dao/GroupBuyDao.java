package com.ssp.closet.dao;

import org.springframework.dao.DataAccessException;

import com.ssp.closet.dto.GroupBuy;


public interface GroupBuyDao {

	//현재 공동구매 참여자 인원 조회
	int getPeopleNum(int productId) throws DataAccessException;

	//공동구매 정보 상세보기
	GroupBuy getGroupBuyDetail(int productId) throws DataAccessException;
}
