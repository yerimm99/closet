package com.ssp.closet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Bid;
import com.ssp.closet.dto.Bookmark;
import com.ssp.closet.dto.Category;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Delivery;
import com.ssp.closet.dto.Review;

public interface ClosetFacade {

	List<Category> getCategoryList();
	Category getCategory(String categoryId);

	List<Product> searchProductList(String keywords);
	List<Product> getProductList(int type, int status);

	void insertAuction(Auction auction);
	Auction getAuction(int productId);
	void updateMaxPrice(int productId);
	List<Auction> getAuctionList();

	Page<Auction> getAuctionByCategoryId(String categoryId, Pageable pageable);
	Page<Auction> findSellAuctionByAccount(Account account, Pageable pageable);
	void deleteAuctionByProductId(int productId);

	void scheduleAuctionEnd(Auction auction);
	void closedAuctionBySupp(Auction auction);

	Page<Auction> getAuctionList(Pageable pageable);

	void createBid(Bid bid);
	boolean isBidPriceExists(int productId, int bidPrice);
	void deleteBid(int productId, String userId);
	void updateResult(String userId);
	Bid findMaxPrice(int productId);		 	  
	//	List<Bid> getBidResultList(String userId);
	List<Bid> getBid(String userId);
	Bid getBid(String userId, int productId);
	Integer countBidByProductId(int productId);


	void insertGroupbuy(Groupbuy groupbuy);
	Groupbuy getGroupbuyDetail(int productId);
	List<Groupbuy> getGroupbuyList();
	Page<Groupbuy> getGroupbuyByCategoryId(String categoryId, Pageable pageable);
	Page<Groupbuy> findSellGroupbuyByAccount(Account account, Pageable pageable);
	Groupbuy findBuyGroupbuyByProductId(int productId);
	void deleteGroupbuyByProductId(int productId);
	Page<Groupbuy> getGroupbuyList(Pageable pageable);

	void createMeet(Meet meet);
	Meet findMeetByUserIdAndProductId(String userId, int productId);
	List<Meet> findMeetByProductId(int productId);
	List<Meet> findMeetByUserId(String userId);
	Integer getMeetCountByProductId(int productId);
	void deleteByUserIdAndProductId(String userId, int productId);

	void createMark(Bookmark bookmark);
	void deleteMark(String userId, int productId);

	public void createDelivery(Delivery delivery);
	List<Delivery> getBuyList(String userId);
	List<Delivery> getSellList(String userId);

	void insertReview(Review review);
	void deleteReview(int orderId);
	List<Review> readReviewListByMe();
	List<Review> readReviewListToMe();

	//Account Connect

	Account getAccount(String userId);

	Account getAccount(String userId, String password);

	void insertAccount(Account account);

	void updateAccount(Account account);

}
