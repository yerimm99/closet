package com.ssp.closet.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssp.closet.dao.AccountDao;
import com.ssp.closet.dao.AuctionDao;
import com.ssp.closet.dao.BookmarkDao;
import com.ssp.closet.dao.GroupbuyDao;
import com.ssp.closet.dao.ProductDao;
import com.ssp.closet.dao.MeetDao;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Bid;
import com.ssp.closet.dto.Bookmark;
import com.ssp.closet.dto.Category;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.Delivery;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Review;
import com.ssp.closet.repository.AccountRepository;
import com.ssp.closet.repository.AuctionRepository;
import com.ssp.closet.repository.BidRepository;
import com.ssp.closet.repository.DeliveryRepository;
import com.ssp.closet.repository.GroupbuyRepository;
import com.ssp.closet.repository.MeetRepository;
import com.ssp.closet.repository.ProductRepository;

@Service
@Transactional
public class ClosetImpl implements ClosetFacade{


	@Autowired  
	private ProductRepository productRepository;
	
	public List<Product> getProductList(int type, int status) {
		return productRepository.findByPtypeAndStatus(type, status);
	}
	
	public List<Product> getProductList() {
		return productRepository.findAll();
	}
	
	
	//경매
	@Autowired
	private AuctionRepository aucRepository;

	public void insertAuction(Auction auction) {
		aucRepository.save(auction);
	}
	public Auction getAuction(int productId) {
		return aucRepository.findByProductId(productId); 
	}
	public void updateMaxPrice(int productId) {
		aucRepository.updatePrice(productId, findMaxPrice(productId).getBidPrice());
	}
	
	public void deleteAuctionByProductId(int productId) {
		aucRepository.deleteByProductId(productId);
	}
	
	public List<Auction> getAuctionByCategoryId(String categoryId) {
        return aucRepository.findByCategoryIdOrderByRegisterDateDesc(categoryId);
    }
	public List<Auction> searchAuctionList(String keywords) {
		return aucRepository.findByNameIgnoreCaseContaining(keywords);
	}
	
	//추가
	public List<Auction> getAuctionByUsed(int used){
		return aucRepository.findByUsedOrderByRegisterDateDesc(used);
	}
	public List<Auction> getAuctionByCategoryIdAndUsed(String categoryId, int used){
		return aucRepository.findByCategoryIdAndUsedOrderByRegisterDateDesc(categoryId, used);
	}
	
	public List<Auction> findSellAuctionByAccount(Account account){
		 return aucRepository.findByAccountOrderByRegisterDateDesc(account);
	}
	
	//나현추가
	public List<Auction> findTop4AuctionOrderByRegisterDate(){
		return aucRepository.findTop4OrderByRegisterDate();
	}
	
	public List<Auction> getAuctionList() {
		return aucRepository.findAllByOrderByRegisterDateDesc();
	}
	
	@Autowired
	private TaskScheduler scheduler;

	public void scheduleAuctionEnd(Auction auction) { //낙찰처리
	    Date closingTime = auction.getEndDate(); // 경매 종료 시간을 가져옴
	    System.out.println(closingTime);
	    Runnable auctionEndTask = new Runnable() {
	        @Override
	        public void run() {
	        	Bid highestBid = findMaxPrice(auction.getProductId());
	            if (highestBid != null && auction.getStatus() != 0) {
	                // 낙찰 처리
	                auction.setWinner(highestBid.getUserId());
	                updateResult(auction.getWinner());
	            }
	            auction.setStatus(0);
            	aucRepository.save(auction);
	        }
	    };

	    // 스케줄 생성: closingTime에 auctionEndTask 실행
	    
	    scheduler.schedule(auctionEndTask, closingTime);
	    System.out.println("Auction end task has been scheduled to execute at " + closingTime);
	}
	
	@Scheduled(cron = "10 * * * * *") // 경매 종료 확인 주기
    public void checkAuctionEnd() {
        // 경매 종료 시간이 현재 시간보다 이전인 경매 조회
        List<Auction> endedAuctions = aucRepository.findEndedAuctions(LocalDateTime.now());
        
        // 각 경매에 대해 최고 입찰가 확인
        for (Auction auction : endedAuctions) {
            Bid highestBid = findMaxPrice(auction.getProductId());
            if (highestBid != null) {
                // 낙찰 처리
                auction.setWinner(highestBid.getUserId());
                updateResult(auction.getWinner());
            }
            auction.setStatus(0);
            aucRepository.save(auction);
        }
    }
	
	public void closedAuctionBySupp(Auction auction) {
		auction.setStatus(0);
		Bid highestBid = findMaxPrice(auction.getProductId());
		if (highestBid != null) {
           // 낙찰 처리
           auction.setWinner(highestBid.getUserId());
           updateResult(auction.getWinner());
        }
        aucRepository.save(auction);
	}
	
	
	//입찰
	@Autowired
	private BidRepository bidRepository;
	
	public void createBid(Bid bid) {
		bidRepository.save(bid);
		updateMaxPrice(bid.getProductId());
	}

	public boolean isBidPriceExists(int productId, int bidPrice) {
		return bidRepository.existsByProductIdAndBidPrice(productId, bidPrice);
	}
	public void deleteBid(int productId, String userId) {
		bidRepository.deleteByProductIdAndUserId(productId, userId);
	}
	public Integer countBidByProductId(int productId) {
		return bidRepository.countByProductId(productId);
	}
	  
	public void updateResult(String userId) {
		bidRepository.updateSuccessResult(userId);
		bidRepository.updateFailResult(userId);
	}
	  
	public Bid findMaxPrice(int productId) {
		return bidRepository.findTopByProductIdOrderByBidPriceDesc(productId);
	}	 
	
	public List<Bid> getBid(String userId) {
		return bidRepository.findByUserId(userId);
	}

	public Bid getBid(String userId, int productId) {
		return bidRepository.findByUserIdAndProductId(userId, productId);
	}
	
	
	//관심상품
	@Autowired
	private BookmarkDao bookmarkDao;

	public void createMark(Bookmark bookmark) {
		bookmarkDao.createMark(bookmark);
	}
	public void deleteMark(String userId, int productId) {
		bookmarkDao.deleteMark(userId, productId);
	}
	
	
	//공동구매
	@Autowired
	private GroupbuyRepository groupbuyRepository;
	
	public void insertGroupbuy(Groupbuy groupbuy) {
		groupbuyRepository.save(groupbuy);
	}
	
	public List<Groupbuy> getGroupbuyByCategoryId(String categoryId) {
        return groupbuyRepository.findByCategoryIdOrderByRegisterDateDesc(categoryId);
    }
	
	public Groupbuy getGroupbuyDetail(int productId) {
		return groupbuyRepository.getReferenceById(productId); 
	}
	
	public void deleteGroupbuyByProductId(int productId) {
		groupbuyRepository.deleteByProductId(productId);
	}
	
	public List<Groupbuy> findSellGroupbuyByAccount(Account account){
		return groupbuyRepository.findByAccountOrderByRegisterDateDesc(account);
	}
	
	public Groupbuy findBuyGroupbuyByProductId(int productId){
		return groupbuyRepository.findByProductId(productId);
	}

	public List<Groupbuy> searchGroupbuyList(String keywords) {
		return groupbuyRepository.findByNameIgnoreCaseContaining(keywords);
	}	
	public List<Groupbuy> getGroupbuyList() {
		return groupbuyRepository.findAllByOrderByRegisterDateDesc();
	}

	
	//공구참여
	@Autowired
	private MeetRepository meetRepository;
	
	public void insertMeet(Meet meet) {
		meetRepository.save(meet);
	}
	
	public Meet findMeetByUserIdAndProductId(String userId, int productId) {
	    return meetRepository.findByUserIdAndProductId(userId, productId);
	}
	
	public List<Meet> findMeetByProductId(int productId){
		return meetRepository.findByProductId(productId);
	}

	public List<Meet> findMeetByUserId(String userId){
		return meetRepository.findByUserId(userId);
	}
	
	public Integer getMeetCountByProductId(int productId) {
		return meetRepository.getMeetCountByProductId(productId);
	}
	
	public void deleteByUserIdAndProductId(String userId, int productId) {
		meetRepository.deleteByUserIdAndProductId(userId, productId);
	}
	
	
	//주문
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	public void createDelivery(Delivery delivery) {
		deliveryRepository.save(delivery);
	}
	
	public List<Delivery> getOrderList(String userId) {
		return deliveryRepository.findAllByUserId(userId);
	}
	
	public Delivery getOrder(int orderId) {
		return deliveryRepository.findByOrderId(orderId);
	}
	
	
	//리뷰
	@Override
	public void insertReview(Review review) {
		// TODO Auto-generated method stub

	}
	@Override
	public void deleteReview(int orderId) {
		// TODO Auto-generated method stub

	}
	@Override
	public List<Review> readReviewListByMe() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Review> readReviewListToMe() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//카테고리
	@Override
	public List<Category> getCategoryList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Category getCategory(String categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	//계정
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private AccountRepository accountRepo;
	
	@Override
	public Account getAccount(String name) {
		return accountDao.getAccount(name);
	}
	@Override
	public Account getAccount(String userId, String password) {
		return accountDao.getAccount(userId, password);
	}
	@Override
	public void insertAccount(Account account) {
		accountRepo.save(account);

	}
	@Override
	public void updateAccount(Account account) {
		accountRepo.save(account);
	}
	
	private final ProductDao productDao;

    public ClosetImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

	/*
	 * @Override public List<Product> getTopRankingProducts() { // Implement the
	 * logic to retrieve the top ranking products // Example: // 1. Use the
	 * productDao to fetch the top ranking products // based on your ranking
	 * criteria (e.g., sales, ratings, views). // Adjust the method name and
	 * parameters based on your productDao implementation. List<Product>
	 * topRankingProducts = productDao.getTopRankingProducts();
	 * 
	 * // 2. Return the list of top ranking products return topRankingProducts; }
	 */
}