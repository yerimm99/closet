package com.ssp.closet.controller.auction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.controller.auction.AuctionForm;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.service.AuctionFormValidator;
import com.ssp.closet.service.ClosetFacade;

@Controller
@SessionAttributes("auctionForm")
public class AuctionFormController {
	
//	@Value("auction/registerForm")
//	private String formViewName;
	@Autowired
	private ClosetFacade closet;
	
	@Autowired
	public void setClosetStore(ClosetFacade closet) {
		this.closet = closet;
	}
	@Autowired
	private AuctionFormValidator validator;
	public void setValidator(AuctionFormValidator validator) {
		this.validator = validator;
	}
	
	@ModelAttribute("auctionForm")
	public AuctionForm createAuctionForm() {
		return new AuctionForm();
	}
	
//	@RequestMapping(method = RequestMethod.GET)
//	public String showForm() {
//		return formViewName;
//	}
	
	@ModelAttribute("categories")
	public List<String> referenceData2() {
		ArrayList<String> categories = new ArrayList<String>();
		categories.add("신발");
		categories.add("아우터");
		categories.add("상의");
		categories.add("하의");
		categories.add("가방");
		categories.add("지갑");
		categories.add("시계");
		categories.add("패션잡화");
		return categories;			
	}
	
	@RequestMapping("/auction/newAuction.do")
	public String initNewAuction(HttpServletRequest request,
			@ModelAttribute("auctionForm") AuctionForm auctionForm
			) throws Exception {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			auctionForm.getAuction().initAuction(account);
			auctionForm.setNewAuction(true);
			return "auction/registerForm";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}
	
	@RequestMapping("/auction/update.do")
	public String editNewAuction(HttpServletRequest request,
			@RequestParam("productId") int productId,
			@ModelAttribute("auctionForm") AuctionForm auctionForm
			) throws ModelAndViewDefiningException {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			Auction existingAuction = closet.getAuction(productId);
			auctionForm.getAuction().initAuction(account);
			if (existingAuction != null) {
				auctionForm.setAuction(existingAuction);
				auctionForm.setNewAuction(false);
			}
			return "auction/registerForm";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}
	
	
	@RequestMapping("/auction/confirmAuction.do")
	protected ModelAndView confirmAuction( //auction 등록 확인 
			@ModelAttribute("auctionForm") AuctionForm auctionForm, 
			SessionStatus status, BindingResult result) {

		validator.validateAuctionForm(auctionForm.getAuction(), result);
		ModelAndView mav1 = new ModelAndView("auction/registerForm");
		if (result.hasErrors()) return mav1;
		
		closet.insertAuction(auctionForm.getAuction()); //등록 
		closet.scheduleAuctionEnd(auctionForm.getAuction());
		ModelAndView mav2 = new ModelAndView("auction/detail");
		mav2.addObject("product", auctionForm.getAuction());
		status.setComplete();  // remove session
		return mav2;
	}
	
	@RequestMapping("/auction/delete.do")
	public String removeAuction(HttpServletRequest request,
			@RequestParam("productId") int productId
			) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			if(closet.countBidByProductId(productId) == 0) {
				closet.deleteAuctionByProductId(productId);
			}
			else {
				return "redirect:/popup/deleteAuction.do";
			}
		}
		else {
			return "redirect:/account/SignonForm.do";
		}
		return "redirect:/closet/mypage.do";
	}

	@RequestMapping("/popup/deleteAuction.do")
	public String showPopup() {
		return "groupbuy/popup";
	}
	
	
}
