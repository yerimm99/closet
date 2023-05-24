//package com.ssp.closet.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.ssp.closet.service.AuctionFormValidator;
//import com.ssp.closet.service.ClosetFacade;
//
//@Controller
//@RequestMapping({"/auction/auctionForm.do","/auction/updateAuctionForm.do"})
//public class AuctionFormController {
//	
//	@Value("CreateAuctionForm") // 수정필요 
//	private String formViewName;
//	@Value("index") // 수정필요 
//	private String successViewName;
//	@Autowired
//	private ClosetFacade closet;
//	
//	@Autowired
//	private AuctionFormValidator validator;
//	public void setValidator(AuctionFormValidator validator) {
//		this.validator = validator;
//	}
//	
//	@ModelAttribute("auctionForm")
//	public AuctionForm formBackingObject(HttpServletRequest request) 
//			throws Exception {
//		if (request.getMethod().equalsIgnoreCase("GET")) {	
//			AuctionForm auctionForm = new AuctionForm();
//			return auctionForm;
//				
//		}
//		else {
//			return new AuctionForm();
//		}
//	}
//	@RequestMapping(method = RequestMethod.GET)
//	public String showForm() {
//		return formViewName;
//	}
//	
//	@RequestMapping(method = RequestMethod.POST)
//	public String onSubmit(
//			HttpServletRequest request, HttpSession session, //세션 사용? 
//			@ModelAttribute("auctionForm") AuctionForm auctionForm,
//			BindingResult result) throws Exception {
//		validator.validate(auctionForm.getAuction().getAcutionPrice(), result);
//		
//		if (result.hasErrors()) return formViewName;
//	
//		try {
//			if (auctionForm.isNewAuction()) {
//				closet.insertAuctionPrice(auctionForm.getAuction()); //입찰 등록 
//			}
//			else {
//				closet.updateAuctionPrice(auctionForm.getAuction()); //입찰가 수정 
//			}
//		}
//		catch (DataIntegrityViolationException ex) {
//			result.rejectValue("auction.auctionPrice","insert price");
//			return formViewName; 
//		}
//		return successViewName;  
//	}
//	
//	@RequestMapping("/auction/confirmAuction.do")
//	protected ModelAndView confirmAuction(
//			@ModelAttribute("auctionForm") AuctionForm auctionForm, 
//			SessionStatus status) {
//		closet.insertAuctionPrice(auctionForm.getAuction());
//		ModelAndView mav = new ModelAndView("ViewOrder");
//		mav.addObject("auction", auctionForm.getAuction());
//		mav.addObject("message", "Thank you, your auction has been submitted.");
//		status.setComplete();  // remove sessionCart and orderForm from session
//		return mav;
//	}
//}
