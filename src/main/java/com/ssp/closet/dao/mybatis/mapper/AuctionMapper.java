package com.ssp.closet.dao.mybatis.mapper;


import java.util.List;

import com.ssp.closet.dto.Auction;

public interface AuctionMapper {
	  void insertAuctionPrice(Auction auction);

	  void updateAuctionPrice(Auction auction); //입찰가 수정 
	  
	  int findMaxAuctionPrice(int productId); //최고 price값 반환 
	
	  void updateSuccessAuctionResult(Auction auction); //auction result 변경 낙찰 시 0 -> 1 
	 
	  void updateFailAuctionResult(Auction auction); //낙찰 실패시 0 -> 2
	  
	  void deleteAuctionByUser(Auction auction); //user 취소에 의해 auction 삭제 
	  
	  List<Auction> getAuctionResultList(String userId); //입찰 현황 리스트 
}
