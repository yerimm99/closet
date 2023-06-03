package com.ssp.closet.controller.auction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
import com.ssp.closet.service.AuctionFormValidator;
import com.ssp.closet.service.ClosetFacade;

@Controller
@RequestMapping({"/auction/registerForm.do", "/auction/editAuction.do"})
public class AuctionFormController {
	
	@Value("auction/registerForm")
	private String formViewName;
	@Value("main/auction")
	private String successViewName;
	@Autowired
	private ClosetFacade closet;
	
	@Autowired
	private AuctionFormValidator validator;
	public void setValidator(AuctionFormValidator validator) {
		this.validator = validator;
	}
	
	@ModelAttribute("auctionForm")
	public AuctionForm createAuctionForm() {
		return new AuctionForm();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}
	
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
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit( //auction 등록 
			HttpServletRequest request, HttpSession session, //세션 사용? 
			@ModelAttribute("auctionForm") AuctionForm auctionForm,
			BindingResult result) throws Exception {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		
		validator.validateAuctionForm(auctionForm.getAuction(), result);
		
		if (result.hasErrors()) return "fromViewName";
		
		Account account = closet.getAccount(userSession.getAccount().getUserId());
		auctionForm.getAuction().initAuction(account);
		
		closet.insertAuction(auctionForm.getAuction()); //등록 

		session.setAttribute("userSession", userSession);
		return successViewName;
		
//		if (result.hasErrors()) return formViewName; // error 발생시 이동할 화면? 수정 필요..
//		try {
//			if (auctionForm.isNewAuction()) {
//				closet.insertAuctionProduct(auctionForm.getAuction()); //등록 
//			}
//			else {
//				closet.updateProduct(auctionForm.getAuction().getProductId()); //수정 
//			}
//		}
//		catch (DataIntegrityViolationException ex) {
//			result.rejectValue("auction.startPrice","insert startPrice");
//			return formViewName; // error 발생시 이동할 화면? 수정 필요..
//		}
//		return successViewName;  //등록 성공시 ViewAuction으로 이동 
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
