package com.ssp.closet.controller.auction;

import javax.servlet.http.HttpServletRequest;

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
import com.ssp.closet.dto.BidId;
import com.ssp.closet.service.ClosetFacade;

@Controller
@SessionAttributes("auction")
public class BidFormController {
	@Autowired
	private ClosetFacade closet;
	
	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return "bid/bidForm";
	}
	
	@RequestMapping({"/bid/newBid.do","/bid/editBid.do"})
	public ModelAndView initBid(HttpServletRequest request,
			@RequestParam("productId") int productId,
			@ModelAttribute("bidForm") BidForm bidForm) 
			throws ModelAndViewDefiningException {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			Auction auction = closet.getAuction(productId);
			bidForm.getBid().initBid(account, auction);

			Bid existingBid = closet.getBid(account.getUserId(), productId);
			bidForm.getBid().initBid(account, auction);
			if (existingBid != null) {
				bidForm.setBid(existingBid);
				bidForm.setNewBid(false);
			} else {
				Bid newBid = new Bid();
	            newBid.initBid(account, auction);
	            bidForm.setBid(newBid); // 새로운 Bid 객체 설정
				bidForm.setNewBid(true);
			}
			ModelAndView mav = new ModelAndView("bid/bidForm");
			mav.addObject("product", auction);
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("redirect:/account/SignonForm.do");
			return mav;
		}
	}
	

//	@RequestMapping("/bid/updatePrice.do")
//	public String initUpdateBid(HttpServletRequest request,
//			@ModelAttribute("auction") Auction auction,
//			@ModelAttribute("bidForm") BidForm bidForm,
//			@RequestParam("bidPrice") int bidPrice) 
//			throws Exception {
//		UserSession userSession = 
//				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
//		if (userSession != null) {
//			Account account = closet.getAccount(userSession.getAccount().getUserId());
//			bidForm.getBid().initBid(account, auction);
//			return "bid/updatePriceForm";
//		}
//		else {
//			return "redirect:/account/SignonForm.do";
//		}
//	}
	
	@RequestMapping("/bid/confirmBid.do")
	protected ModelAndView confirmBid( //Bid 등록 
			@ModelAttribute("bidForm") BidForm bidForm,
			@RequestParam("bidPrice") int bidPrice,
			SessionStatus status, BindingResult result) throws ModelAndViewDefiningException {
		
		//validator.validate(accountForm, result);

		if (result.hasErrors()) { 
			ModelAndView mav = new ModelAndView("auction/detail");
			return mav;
		}
	
		Auction auction = bidForm.getBid().getAuction();
		try {
			if (bidForm.isNewBid()) {
				closet.createBid(bidForm.getBid());
				closet.updateMaxPrice(auction);
			}
			else {
				closet.updateBidPrice(bidForm.getBid().getId().getProductId(), bidPrice);
				closet.updateMaxPrice(auction);
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("bid.bidPrice", "BIDPRICE_ALREADY_EXISTS",
					"bidPrice already exists: choose a different price.");
			ModelAndView mav = new ModelAndView("auction/detail");
			mav.addObject("product", auction);
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
	
	@RequestMapping("/bid/deleteBid.do")
	public String removeBid(
			@RequestParam("productId") int productId
		) throws Exception {
		closet.deleteBid(productId);
		return "main/myPage";
	}
}
