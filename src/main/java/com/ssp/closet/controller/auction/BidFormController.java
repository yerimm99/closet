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
@SessionAttributes("bidForm")
public class BidFormController {
	@Autowired
	private ClosetFacade closet;
	
	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}
	
	@ModelAttribute("bidForm")
	public BidForm createBidForm() {
		return new BidForm();
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

	
	@RequestMapping("/bid/confirmBid.do")
	protected ModelAndView confirmBid( //Bid 등록 
			@ModelAttribute("bidForm") BidForm bidForm,
			SessionStatus status, BindingResult result) throws ModelAndViewDefiningException {
		Bid bid = bidForm.getBid();
		
		if (bid.getBidPrice() < bid.getAuction().getStartPrice()) {
            result.rejectValue(
                "bidPrice",
                "START_PRICE_INVALID",
                "입찰가는 경매 시작가 이상이어야 합니다."
            );
        }
		if (closet.isBidPriceExists(bid.getProductId(), bid.getBidPrice())) {
	        result.rejectValue("bidPrice", "BIDPRICE_ALREADY_EXISTS",
	                "이미 존재하는 입찰가입니다.");
		}
		if (result.hasErrors()) { 
			ModelAndView mav = new ModelAndView("bid/bidForm");
			mav.addObject("product", bid.getAuction());
			return mav;
		}
		closet.createBid(bid);
		bid.getAuction().setPrice(bid.getBidPrice());
		ModelAndView mav = new ModelAndView("redirect:/closet/auction.do");
		mav.addObject("product", bid.getAuction());
		status.setComplete();  // remove session
		return mav;
	}

	
	@RequestMapping("/bid/deleteBid.do")
	public String removeBid(HttpServletRequest request,
			@RequestParam("productId") int productId
		) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			closet.deleteBid(productId, account.getUserId());
		}
		return "redirect:/myPage/buyAuction.do";
	}
}
