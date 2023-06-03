package com.ssp.closet.controller.auction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.service.ClosetFacade;

@Controller
public class BidFormController {
	@Autowired
	private ClosetFacade closet;
	
	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}
	@RequestMapping("/auction/auctionForm.do")
	public String onSubmit( //Bid 등록 
			HttpServletRequest request, HttpSession session, //세션 사용? 
			@RequestParam("productid") int productId,
			@ModelAttribute("bidForm") BidForm bidForm,
			BindingResult result) throws Exception {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		
		if (result.hasErrors()) return "auction/auctionForm";
		
		Account account = closet.getAccount(userSession.getAccount().getUserId());
		Auction auction = closet.getAuctionDetail(productId);
		bidForm.getBid().initBid(account, auction);
		closet.createBid(bidForm.getBid()); //등록 

		session.setAttribute("userSession", userSession);
		return "main/auction";
	}
	
	@RequestMapping("/auction/auctionPriceUpdateForm.do")
	public String onSubmit2( 
			HttpServletRequest request, HttpSession session, //세션 사용? 
			@RequestParam("bidId") int bidId,
			@RequestParam("bidPrice") int bidPrice,
			@ModelAttribute("bidForm") BidForm bidForm,
			BindingResult result) throws Exception {

		if (result.hasErrors()) return "auction/auctionPriceUpdateForm";

		closet.updatePrice(bidId, bidPrice);
		return "main/auction";
	}
}
