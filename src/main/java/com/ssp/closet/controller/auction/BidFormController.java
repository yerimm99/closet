package com.ssp.closet.controller.auction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Bid;
import com.ssp.closet.service.AuctionFormValidator;
import com.ssp.closet.service.ClosetFacade;

@Controller
@RequestMapping({"/bid/newBid.do","/bid/editBid.do"})
public class BidFormController {
	@Autowired
	private ClosetFacade closet;
	
	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}
//	
//	@Autowired
//	private AuctionFormValidator validator;
//	public void setValidator(AuctionFormValidator validator) {
//		this.validator = validator;
//	}
	
	@ModelAttribute("bidForm")
	public BidForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");

		Account account = closet.getAccount(userSession.getAccount().getUserId());
		Bid bid = closet.getBid(account.getUserId());
		if(bid != null) {
			return new BidForm(closet.getBid(account.getUserId()));
		} else {
			return new BidForm();
		}
	}
	
//	@RequestMapping("/bid/newBid.do")
//	public String initNewBid(HttpServletRequest request,
//			@ModelAttribute("bidForm") BidForm bidForm,
//			@RequestParam("productid") int productId
//			) throws Exception {
//		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
//
//		Account account = closet.getAccount(userSession.getAccount().getUserId());
//		bidForm.getBid().initBid(account, productId);
//		return "bid/bidForm";
//	
//	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView confirmBid( //Bid 등록 
			@ModelAttribute("auction") Auction auction,
			@ModelAttribute("bidForm") BidForm bidForm,
			@RequestParam("bidPrice") int bidPrice,
			SessionStatus status, BindingResult result) throws ModelAndViewDefiningException {
		
		//validator.validate(accountForm, result);

		if (result.hasErrors()) { 
			ModelAndView mav = new ModelAndView("auction/detail");
			return mav;
		}
		try {
			if (bidForm.isNewBid()) {
				closet.createBid(bidForm.getBid());
				closet.updateMaxPrice(auction);
			}
			else {
				closet.updateBidPrice(bidForm.getBid().getBidId(), bidPrice);
				closet.updateMaxPrice(auction);
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("bid.bidPrice", "BIDPRICE_ALREADY_EXISTS",
					"bidPrice already exists: choose a different price.");
			ModelAndView mav = new ModelAndView("auction/detail");
			return mav;
		}
//		bidForm.getBid().initBid(bidForm.getBid().getUserId(), bidForm.getBid().getProductId());
//		closet.createBid(bidForm.getBid()); //등록 
//		closet.updateMaxPrice(auction); //최고가 갱신
		ModelAndView mav = new ModelAndView("auction/detail");
		mav.addObject("product", auction);
		status.setComplete();  // remove session
		return mav;
	}
//	
//	@RequestMapping("/bid/newAuction.do")
//	public String initNewAuction(HttpServletRequest request,
//			@ModelAttribute("auctionForm") AuctionForm auctionForm
//			) throws Exception {
//		
//		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");	
//		Account account = closet.getAccount(userSession.getAccount().getUserId());
//		auctionForm.getAuction().initAuction(account);
//		return "auction/registerForm";
//	
//	}
//	
//	@RequestMapping("/bid/bidPriceUpdateForm.do")
//	public ModelAndView conFirmUpdate(
//			HttpServletRequest request,
//			@RequestParam("bidPrice") int bidPrice,
//			@ModelAttribute("bidForm") BidForm bidForm,
//			BindingResult result) throws ModelAndViewDefiningException {
//		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");	
//		Account account = closet.getAccount(userSession.getAccount().getUserId());
//		if (result.hasErrors()) return "auction/auctionPriceUpdateForm";
//
//		closet.updateBidPrice(bidForm.getBid().getBidId(), bidPrice);
//		return "main/auction";
//	}
}
