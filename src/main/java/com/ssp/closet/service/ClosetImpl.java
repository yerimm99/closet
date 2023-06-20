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

	public List<Product> searchProductList(String keywords) {
		return productRepository.findByNameIgnoreCaseContaining(keywords);
	}
//	public Product getProductDetail(int productId) {
//		return productRepository.findByProductId(productId);
//	}
//
//	public void updateProduct(String description, String picture1, String picture2, String picture3, String picture4, int productId) {
//		productRepository.updateByProductId(description, picture1, picture2, picture3, picture4, productId);
//	};
//	public void deleteProduct(int productId) {
//		productRepository.deleteByProductIdIfGroupBuyPeopleNumIsOne(productId);
//	};
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
	
	public Page<Auction> getAuctionByCategoryId(String categoryId, Pageable pageable) {
        return aucRepository.findByCategoryId(categoryId, pageable);
    }
	
	//추가
	public Page<Auction> getAuctionByUsed(int used, Pageable pageable){
		return aucRepository.findByUsed(used, pageable);
	}
	public Page<Auction> getAuctionByCategoryIdAndUsed(String categoryId, int used, Pageable pageable){
		return aucRepository.findByCategoryIdAndUsed(categoryId, used, pageable);
	}
	
	public Page<Auction> findSellAuctionByAccount(Account account, Pageable pageable){
		 return aucRepository.findByAccount(account, pageable);
	}
	
	//나현추가
	public List<Auction> findTop4AuctionOrderByRegisterDate(){
		return aucRepository.findTop4OrderByRegisterDate();
	}
	
	@Transactional
	public Page<Auction> getAuctionList(Pageable pageable) {
		return aucRepository.findAll(pageable);//페이징 객체만들어서 반환
	}
	
	@Autowired
	private TaskScheduler scheduler;

	public void scheduleAuctionEnd(Auction auction) { //낙찰처리
	    Date closingTime = auction.getEndDate(); // 경매 종료 시간을 가져옴

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
	
//	@Scheduled(fixedDelay = 60000) // 경매 종료 확인 주기 (1분마다 실행)
//    public void checkAuctionEnd() {
//        // 경매 종료 시간이 현재 시간보다 이전인 경매 조회
//        List<Auction> endedAuctions = aucRepository.findEndedAuctions(LocalDateTime.now());
//        
//        // 각 경매에 대해 최고 입찰가 확인
//        for (Auction auction : endedAuctions) {
//            Bid highestBid = findMaxPrice(auction.getProductId());
//            if (highestBid != null) {
//                // 낙찰 처리
//                auction.setWinner(highestBid.getUserId());
//                auction.setStatus(0);
//                aucRepository.save(auction);
//                updateResult(auction.getWinner());
//            }else {
//            	auction.setStatus(0);
//            	aucRepository.save(auction);
//            }
//        }
//    }
	
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
	  
//	public List<Bid> getBidResultList(String userId) {
//		return bidRepository.findResultByBidder(userId);
//	}
	
	public List<Bid> getBid(String userId) {
		return bidRepository.findByUserId(userId);
	}

	public Bid getBid(String userId, int productId) {
		return bidRepository.findByUserIdAndProductId(userId, productId);
	}

	@Autowired
	private BookmarkDao bookmarkDao;

	public void createMark(Bookmark bookmark) {
		bookmarkDao.createMark(bookmark);
	}
	public void deleteMark(String userId, int productId) {
		bookmarkDao.deleteMark(userId, productId);
	}
	
	@Autowired
	@Qualifier("jpaAuctionDao")
	private AuctionDao auctionDao;
	
	public List<Auction> getAuctionList() {
		return auctionDao.getAuctionList();
	}
	
	
	@Autowired
	private GroupbuyRepository groupbuyRepository;
	
	public void insertGroupbuy(Groupbuy groupbuy) {
		groupbuyRepository.save(groupbuy);
	}
	
	public Page<Groupbuy> getGroupbuyByCategoryId(String categoryId, Pageable pageable) {
        return groupbuyRepository.findByCategoryId(categoryId, pageable);
    }
	
	public Groupbuy getGroupbuyDetail(int productId) {
		return groupbuyRepository.getReferenceById(productId); 
	}
	
	public void deleteGroupbuyByProductId(int productId) {
		groupbuyRepository.deleteByProductId(productId);
	}
	
	public Page<Groupbuy> findSellGroupbuyByAccount(Account account, Pageable pageable){
		return groupbuyRepository.findByAccount(account, pageable);
	}
	
	public Groupbuy findBuyGroupbuyByProductId(int productId){
		return groupbuyRepository.findByProductId(productId);
	}
	
	@Transactional
	public Page<Groupbuy> getGroupbuyList(Pageable pageable) {
		return groupbuyRepository.findAll(pageable);//페이징 객체만들어서 반환
	}

	
	@Autowired
	@Qualifier("jpaGroupbuyDao")
	private GroupbuyDao groupbuyDao;
	
	public List<Groupbuy> getGroupbuyList(){
		return groupbuyDao.getGroupbuyList();
	}
	
	
	@Autowired
	private MeetRepository meetRepository;
	
	public void createMeet(Meet meet) {
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
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	public void createDelivery(Delivery delivery) {
		deliveryRepository.save(delivery);
	}
	
	
	@Override
	public List<Delivery> getBuyList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Delivery> getSellList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
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
	
	@Autowired
	private AccountDao accountDao;
	
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
		// TODO Auto-generated method stub

	}
	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub

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