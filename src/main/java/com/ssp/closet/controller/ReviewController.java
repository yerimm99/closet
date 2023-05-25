//package com.ssp.closet.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.ssp.closet.dto.Review;
//import com.ssp.closet.service.ReviewService;
//
//@Controller
//@RequestMapping("/review")
//public class ReviewController {
//	
//	@Autowired
//	ReviewService reviewService;
//	@Autowired
//	Review review;
//
//	@RequestMapping("/insert")
//	public String insertReview(ReviewCommand reviewCommand) {
//		//command객체 이용해서 리뷰객체만들어서 service 실행
//		return "";
//	}
//	
//	@RequestMapping("/delete")
//	public void deleteReview(
//			@RequestParam(value = "orderId") int orderId) {
//		return;
//	}
//
//	@RequestMapping("/listByMe")
//	public ModelAndView readReviewListByMe() {
//		ModelAndView mav = new ModelAndView();
//		return mav;
//	}
//	
//	@RequestMapping("/listToMe")
//	public ModelAndView readReviewListToMe() {
//		ModelAndView mav = new ModelAndView();
//		return mav;
//	}
//}
