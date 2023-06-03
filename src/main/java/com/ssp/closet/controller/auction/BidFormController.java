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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.service.ClosetFacade;

@Controller
@SessionAttributes("bidForm")
public class BidFormController {
	@Autowired
	private ClosetFacade closet;
	
	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}
	
	@RequestMapping("/bid/newBid.do")
	public String initNewBid(HttpServletRequest request,
			@ModelAttribute("bidForm") BidForm bidForm,
			@RequestParam("productid") int productId
			) throws ModelAndViewDefiningException {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");

		Account account = closet.getAccount(userSession.getAccount().getUserId());
		bidForm.getBid().initBid(account, productId);
		return "bid/bidForm";
	
	}
	
	@RequestMapping("/bid/confirmBid.do")
	protected ModelAndView confirmBid( //Bid 등록 
			@ModelAttribute("bidForm") BidForm bidForm, 
			SessionStatus status) throws Exception {

		closet.createBid(bidForm.getBid()); //등록 
		ModelAndView mav = new ModelAndView("auction/detail");
		//mav.addObject("product", auctionForm.getAuction());
		status.setComplete();  // remove session
		return mav;
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
