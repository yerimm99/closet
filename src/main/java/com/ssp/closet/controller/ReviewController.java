/*
 * package com.ssp.closet.controller;
 * 
 * import org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.validation.BindingResult; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod;
 * 
 * import com.ssp.closet.service.ReviewService; import
 * com.ssp.closet.service.ReviewFormValidator;
 * 
 * @Controller
 * 
 * @RequestMapping("/reviews") public class ReviewController {
 * 
 * private ReviewService reviewService;
 * 
 * public ReviewController(ReviewService reviewService) { this.reviewService =
 * reviewService; }
 * 
 * // GET 요청 처리 - 리뷰 작성 폼 보여주기
 * 
 * @RequestMapping(value = "/new", method = RequestMethod.GET) public String
 * showReviewForm(Model model) { ReviewCommand reviewCommand = new
 * ReviewCommand(); model.addAttribute("reviewCommand", reviewCommand); return
 * "reviewForm"; // 리뷰 작성 폼 JSP 파일 이름 }
 * 
 * // POST 요청 처리 - 리뷰 데이터 저장
 * 
 * @RequestMapping(value = "/new", method = RequestMethod.POST) public String
 * submitReviewForm(@ModelAttribute("reviewCommand") ReviewCommand
 * reviewCommand, BindingResult result, Model model) { new
 * ReviewFormValidator().validate(reviewCommand, result);
 * 
 * if (result.hasErrors()) { // 오류가 있을 경우 폼을 다시 보여주고 오류 메시지 전달 return
 * "reviewForm"; } else { // 리뷰 생성 및 저장
 * reviewService.createReview(reviewCommand);
 * 
 * // 성공 페이지로 이동 return "successPage"; // 리뷰 작성 성공 시 보여줄 JSP 파일 이름 } }
 * 
 * // 기타 메서드 및 의존성 주입 등 생략 }
 */
=======
//package com.ssp.closet.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.ssp.closet.service.ReviewService;
//import com.ssp.closet.service.ReviewFormValidator;
//
//@Controller
//@RequestMapping("/reviews")
//public class ReviewController {
//    
//	private ReviewService reviewService;
//    
//    public ReviewController(ReviewService reviewService) {
//        this.reviewService = reviewService;
//    }
//    
//    // GET 요청 처리 - 리뷰 작성 폼 보여주기
//    @RequestMapping(value = "/new", method = RequestMethod.GET)
//    public String showReviewForm(Model model) {
//        ReviewCommand reviewCommand = new ReviewCommand();
//        model.addAttribute("reviewCommand", reviewCommand);
//        return "reviewForm"; // 리뷰 작성 폼 JSP 파일 이름
//    }
//    
//    // POST 요청 처리 - 리뷰 데이터 저장
//    @RequestMapping(value = "/new", method = RequestMethod.POST)
//    public String submitReviewForm(@ModelAttribute("reviewCommand") ReviewCommand reviewCommand, BindingResult result, Model model) {
//        new ReviewFormValidator().validate(reviewCommand, result);
//        
//        if (result.hasErrors()) {
//            // 오류가 있을 경우 폼을 다시 보여주고 오류 메시지 전달
//            return "reviewForm";
//        } else {
//            // 리뷰 생성 및 저장
//            reviewService.createReview(reviewCommand);
//            
//            // 성공 페이지로 이동
//            return "successPage"; // 리뷰 작성 성공 시 보여줄 JSP 파일 이름
//        }
//    }
//    
//    // 기타 메서드 및 의존성 주입 등 생략
//}
