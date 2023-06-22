//package com.ssp.closet.controller;
//
//import com.ssp.closet.dto.LikeMark;
//import com.ssp.closet.dto.Product;
//import com.ssp.closet.dto.Account;
//import com.ssp.closet.dto.Auction;
//
//import com.ssp.closet.repository.LikeMarkRepository;
//import com.ssp.closet.repository.ProductRepository;
//import com.ssp.closet.repository.AccountRepository;
//import com.ssp.closet.repository.AuctionRepository;
//import com.ssp.closet.repository.GroupbuyRepository;
//
//import java.util.Optional;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/likes")
//public class LikeController {
//
//    private final ProductRepository productRepository;
//    private final LikeMarkRepository likeMarkRepository;
//    private final AccountRepository accountRepository;
//    private final AuctionRepository auctionRepository;
//    private final GroupbuyRepository goupbuyRepository;
//
//    @Autowired
//    public LikeController(ProductRepository productRepository, LikeMarkRepository likeMarkRepository, AccountRepository accountRepository, AuctionRepository auctionRepository, GroupbuyRepository goupbuyRepository) {
//    	this.productRepository = productRepository;
//        this.likeMarkRepository = likeMarkRepository;
//        this.accountRepository = accountRepository;
//        this.auctionRepository = auctionRepository;
//        this.goupbuyRepository = goupbuyRepository;
//    }
//
//    @GetMapping("/like.do/{productId}/users/{userId}")
//    public ResponseEntity<String> addLikeMark(
//            HttpSession session,
//            @PathVariable("productId") int productId,
//            @PathVariable("userId") String userId) {
//            
//    	
//
//        // Retrieve userId from session
//    	UserSession user2 = (UserSession)session.getAttribute("userSession");
//    	String loggedInUserId = (String) user2.getAccount().getUserId();
//    	//System.out.println(loggedInUserId);
//        // Ensure that the logged-in user matches the requested userId
//        if (!loggedInUserId.equals(userId)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인한 사용자와 요청한 사용자가 일치하지 않습니다");
//        }
//
//        Product product = productRepository.findById(productId).orElse(null);
//        if (product == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("상품을 찾을 수 없습니다");
//        }
//
//        Account user = accountRepository.findById(userId).orElse(null);
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다");
//        }
//
//        LikeMark likeMark = likeMarkRepository.findByProductAndAccount(product, user);
//        if (likeMark != null) {
//            // If the user already liked the product, increment the existing mark
//            likeMark.setMark(likeMark.getMark() + 1);
//        } else {
//            // If the user didn't like the product before, create a new LikeMark entry
//            likeMark = new LikeMark();
//            likeMark.setProduct(product);
//            likeMark.setAccount(user);
//            likeMark.setMark(1);
//        }
//
//        likeMarkRepository.save(likeMark);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body("관심상품이 등록되었습니다");
//    }
//}
