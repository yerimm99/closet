//package com.ssp.closet.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.ssp.closet.dto.Auction;
//import com.ssp.closet.repository.AuctionRepository;
//
//public class AuctionServiceImpl implements AuctionService {
//	@Autowired
//	private AuctionRepository AuctionRepo;
//
//
//	public List<Auction> getAuctionResultList(String userId) {
//		return AuctionRepo.getAuctionResultList(userId);
//	}
//	
//	@Override
//	public void insertAuctionPrice(Auction auction) {
//		AuctionRepo.save(auction);		
//	}
//
//	@Override
//	public void updateAuctionPrice(String auctionId, Auction auction) {
//		AuctionRepo.save(auction);				
//	}
//
//	@Override
//	public void deleteAuctionByUser(String auctionId) {
//		AuctionRepo.deleteById(auctionId);
//	}
//	
//	public int findMaxAuctionPrice(int productId) {
//		
//	}
//}
