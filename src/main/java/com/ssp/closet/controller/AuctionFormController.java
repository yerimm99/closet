package com.ssp.closet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.service.ClosetFacade;

//validator 빼고 일단 완성 
@Controller
@RequestMapping({"/auction/registerForm.do","/auction/editAuction.do"})
public class AuctionFormController {
	
	@Value("EditAuctionForm")
	private String formViewName;
	@Value("index")
	private String successViewName;
	@Autowired
	private ClosetFacade closet;
	
//	@Autowired
//	private AuctionFormValidator validator;
//	public void setValidator(AuctionFormValidator validator) {
//		this.validator = validator;
//	}
	
	@ModelAttribute("auctionForm")
	public AuctionForm createAuctionForm() {
		return new AuctionForm();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit( //auction 등록 
			HttpServletRequest request, HttpSession session, //세션 사용? 
			@ModelAttribute("auctionForm") AuctionForm auctionForm,
			BindingResult result) throws Exception {
		
		if (result.hasErrors()) return formViewName; // error 발생시 이동할 화면? 수정 필요..
		try {
			if (auctionForm.isNewAuction()) {
				closet.insertAuctionProduct(auctionForm.getAuction()); //등록 
			}
			else {
				closet.updateProduct(auctionForm.getAuction().getProductId()); //수정 
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("auction.auctionPrice","insert price");
			return formViewName; // error 발생시 이동할 화면? 수정 필요..
		}
		return successViewName;  //등록 성공시 ViewAuction으로 이동 
	}
	
//	@RequestMapping("/auction/confirmAuction.do")
//	protected ModelAndView confirmAuction( //auction 등록 확인 
//			@ModelAttribute("auctionForm") AuctionForm auctionForm, 
//			SessionStatus status) {
//		ModelAndView mav = new ModelAndView("ViewAuction");
//		mav.addObject("auction", auctionForm.getAuction());
//		mav.addObject("message", "Thank you, your auction has been submitted.");
//		//status.setComplete();  // remove session
//		return mav;
//	}
}
