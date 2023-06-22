package com.ssp.closet.service;

import java.util.List;


import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Bid;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.LikeMark;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Delivery;
import com.ssp.closet.dto.Review;

public interface ClosetFacade {
   
   Product getProduct(int productId);
   
   //경매
   void insertAuction(Auction auction);
   Auction getAuction(int productId);
   void updateMaxPrice(int productId);
   List<Auction> getAuctionList();
   List<Auction> getAuctionByCategoryId(String categoryId);
   List<Auction> searchAuctionList(String keywords);
   //추가
   List<Auction> getAuctionByUsed(int used);
   List<Auction> getAuctionByCategoryIdAndUsed(String categoryId, int used);
   List<Auction> findSellAuctionByAccount(Account account);
   void deleteAuctionByProductId(int productId);

   void scheduleAuctionEnd(Auction auction);
   void closedAuctionBySupp(Auction auction);
   
   List<Auction> findTop4AuctionOrderByRegisterDate();
   
   
   //입찰
   void createBid(Bid bid);
   void insertBid(Bid bid);
   boolean isBidPriceExists(int productId, int bidPrice);
   void deleteBid(int productId, String userId);
   void updateResult(String userId, int productId);
   Bid findMaxPrice(int productId);            
   List<Bid> getBid(String userId);
   Bid getBid(String userId, int productId);
   Integer countBidByProductId(int productId);

   
   //공동구매
   void insertGroupbuy(Groupbuy groupbuy);
   Groupbuy getGroupbuyDetail(int productId);
   List<Groupbuy> getGroupbuyList();
   List<Groupbuy> getGroupbuyByCategoryId(String categoryId);
   List<Groupbuy> findSellGroupbuyByAccount(Account account);
   Groupbuy findBuyGroupbuyByProductId(int productId);
   void deleteGroupbuyByProductId(int productId);
   List<Groupbuy> searchGroupbuyList(String keywords);

   void scheduleGroupbuyEnd (Groupbuy groupbuy);

   List<Groupbuy> findTop4GroupbuyOrderByRegisterDate();

   //공구참여
   void insertMeet(Meet meet);
   Meet findMeetByUserIdAndProductId(String userId, int productId);
   List<Meet> findMeetByProductId(int productId);
   List<Meet> findMeetByUserId(String userId);
   Integer getMeetCountByProductId(int productId);
   void deleteByUserIdAndProductId(String userId, int productId);
   
   
   //관심상품
   void createLike(LikeMark like);
   void deleteLike(Product product, Account account);
   List<LikeMark> findLikeMark(Account account);
   Integer getLikeSum(int productId);
   LikeMark cheakLikeMark(Product product, Account account);
//   int getLikeByProductAndUser(Product product, Account account);
   
   //주문
   void createDelivery(Delivery delivery);
   List<Delivery> getOrderList(String userId);
   Delivery getOrder(int orderId);
   Delivery getOrderByUserIdAndProductId(String userId, int productId);
   
   
   //리뷰
   void insertReview(Review review);
   List<Review> readReviewListToMe(String userId);
   Review findReviewByOrderId(int orderId);
   String userRating(String userId);

   
   //계정
   Account getAccount(String userId);
   Account getAccount(String userId, String password);
   void createAccount(Account account);

   //랭킹
   List<Auction> getAuctionSortedByLikeCount();
   List<Groupbuy> getGroupbuySortedByLikeCount();
   List<Auction> getAuctionRankingByReviewRating();
   List<Groupbuy> getGroupbuyRankingByReviewRating();
}