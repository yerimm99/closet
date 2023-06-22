package com.ssp.closet.controller;

import com.ssp.closet.dto.Review;
import com.ssp.closet.repository.ReviewRepository;
import com.ssp.closet.service.ClosetFacade;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Delivery;
import com.ssp.closet.dto.Groupbuy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"reviewList", "productList"})
public class ReviewController {
	private ClosetFacade closet;

	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}

	@GetMapping("/review/registerForm.do")
	public String getIndexPage(HttpServletRequest request, ModelMap model, 
			@RequestParam("productId") int productId) throws Exception {
		Product product = closet.getProduct(productId);
		model.put("product", product);
		return  "/review/registerForm";
	}

	@PostMapping ("/review/registerForm.do")
	public String createReview(HttpServletRequest request,
			@RequestParam("productId") int productId,
			@RequestParam("rating") float rating, 
			@RequestParam("content") String content) 
					throws Exception{
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");	
		Account account = closet.getAccount(userSession.getAccount().getUserId());
		String suppId = closet.getProduct(productId).getAccount().getUserId();
		Delivery delivery=closet.getOrderByUserIdAndProductId(account.getUserId(), productId);

		Date date = new Date();

		Review newReview = new Review();
		newReview.setContent(content);
		newReview.setOrderId(delivery.getOrderId());;
		newReview.setRating(rating);
		newReview.setUserId(suppId);
		newReview.setWriteDate(date);

		closet.insertReview(newReview);

		return  "redirect:/closet/mypage.do";
	}

	//공동구매 상품 리스트 보기
	@RequestMapping("/review/list.do")
	public String viewReviewList(HttpServletRequest request,
			ModelMap model) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		List<Review> reviews = closet.readReviewListToMe(userSession.getAccount().getUserId());
		List<Product> prdList = new ArrayList<Product>();
		for(Review r : reviews) {
			prdList.add(((closet.getOrder(r.getOrderId())).getProduct()));
		}
		
		PagedListHolder<Review> reviewList = new PagedListHolder<Review>(reviews);
		PagedListHolder<Product> productList = new PagedListHolder<Product>(prdList);//경매게시글에 대한 페이징 객체
		
		reviewList.setPageSize(1);
		productList.setPageSize(1);
		model.put("reviewList", reviewList);
		model.put("productList", productList);
		return "review/listToMe";
	}

	@RequestMapping("/review/list.do2")
	public String viewReviewList_page(
			@ModelAttribute("reviewList") PagedListHolder<Review> reviewList,
			@ModelAttribute("productList") PagedListHolder<Product> productList,
			@RequestParam("pageName") String page, 
			ModelMap model) throws Exception {
		if ("next".equals(page)) {
			reviewList.nextPage();
			productList.nextPage();
		}
		else if ("previous".equals(page)) {
			reviewList.previousPage();
			productList.previousPage();
		}
		model.put("reviewList", reviewList);
		model.put("productList", productList);
		return "review/listToMe";
	}


}
