package com.ssp.closet.controller.auction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
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
//	@Autowired
//	private AuctionFormValidator validator;
//	public void setValidator(AuctionFormValidator validator) {
//		this.validator = validator;
//	}
	
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
			) throws ModelAndViewDefiningException {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			auctionForm.getAuction().initAuction(account);
			return "auction/registerForm";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}
	
//	@RequestMapping("/auction/newAuctionSubmitted.do")
//	public String bindAndValidateAuction( 
//			HttpServletRequest request,  
//			@ModelAttribute("auctionForm") AuctionForm auctionForm,
//			BindingResult result) throws Exception {
//
//		validator.validateAuctionForm(auctionForm.getAuction(), result);
//		
//		if (result.hasErrors()) return "formViewName";
//		return "auction/detail";
//		
//	}
	
	@RequestMapping("/auction/confirmAuction.do")
	protected ModelAndView confirmAuction( //auction 등록 확인 
			@ModelAttribute("auctionForm") AuctionForm auctionForm, 
			SessionStatus status) {
		closet.insertAuction(auctionForm.getAuction()); //등록 
		ModelAndView mav = new ModelAndView("auction/detail");
		mav.addObject("product", auctionForm.getAuction());
		status.setComplete();  // remove session
		return mav;
	}
}
