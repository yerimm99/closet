package com.ssp.closet.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.service.ClosetFacade;

@Controller
@SessionAttributes("orderForm")
public class OrderFormController {
	
	@Autowired
	private ClosetFacade closet;
	
	@Autowired
	public void setClosetStore(ClosetFacade closet) {
		this.closet = closet;
	}

	@ModelAttribute("orderForm")
	public OrderForm createOrderForm() {
		return new OrderForm();
	}
	
	@ModelAttribute("creditCardTypes")
	public List<String> referenceData() {
		ArrayList<String> creditCardTypes = new ArrayList<String>();
		creditCardTypes.add("Visa");
		creditCardTypes.add("MasterCard");
		creditCardTypes.add("American Express");
		return creditCardTypes;			
	}
	
//	@Autowired
//	private OrderValidator validator;
//	public void setValidator(OrderValidator validator) {
//		this.validator = validator;
//	}
	
	@RequestMapping("/order/registerForm.do")  //order 등록 
	public String initNewOrder(HttpServletRequest request,
			@RequestParam("productId") int productId,
			@ModelAttribute("orderForm") OrderForm orderForm,
			ModelMap model
			) throws ModelAndViewDefiningException {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			Groupbuy groupbuy = closet.getGroupbuyDetail(productId);
			orderForm.getOrder().initOrder(account, groupbuy);
			Groupbuy product = closet.getGroupbuyDetail(productId);
			model.put("product", product);
			return "order/registerForm";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}
	
//	@RequestMapping("/order/confirmOrder.do")
//	protected ModelAndView confirmGroupbuy( //auction 등록 확인 
//			@ModelAttribute("orderForm") OrderForm orderForm, 
//			SessionStatus status, BindingResult result) {
//
////		validator.validateGroupbuyForm(orderForm.getOrder(), result);
////		ModelAndView mav1 = new ModelAndView("order/registerForm");
////		if (result.hasErrors()) return mav1;
//		
//		closet.insertOrder(orderForm.getOrder()); //등록 
//		ModelAndView mav2 = new ModelAndView("order/detail");
//		mav2.addObject("product", orderForm.getOrder());
//		status.setComplete();  // remove session
//		return mav2;
//	}
	
	@RequestMapping("/order/register.do")
	protected String confirmGroupbuy( //auction 등록 확인 
			@ModelAttribute("orderForm") OrderForm orderForm, 
			SessionStatus status, BindingResult result) {

//		validator.validateGroupbuyForm(orderForm.getOrder(), result);
//		ModelAndView mav1 = new ModelAndView("order/registerForm");
//		if (result.hasErrors()) return mav1;
		orderForm.getOrder().setExpiryDate(orderForm.convertToFormattedDate(orderForm.getOrder().getExpiryDate()));
		closet.createDelivery(orderForm.getOrder()); //등록 
		Meet meet = closet.findMeetByUserIdAndProductId(orderForm.getOrder().getUserId(), orderForm.getOrder().getProductId());
		meet.setMeetResult(3);
		closet.createMeet(meet);
//		ModelAndView mav2 = new ModelAndView("order/detail");
//		mav2.addObject("product", orderForm.getOrder());
		status.setComplete();  // remove session
		return "redirect:/closet/mypage.do";
	}
}
