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
import com.ssp.closet.service.OrderFormValidator;

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
	
	@Autowired
	private OrderFormValidator validator;
	public void setValidator(OrderFormValidator validator) {
		this.validator = validator;
	}
	
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
	
	@RequestMapping("/order/register.do")
	protected ModelAndView confirmOrder(
			@ModelAttribute("orderForm") OrderForm orderForm, 
			@RequestParam("sample4_postcode") String postCode,
            @RequestParam("address1") String address1,
            @RequestParam("address2") String address2,
			SessionStatus status, BindingResult result) {

		validator.validateOrderForm(orderForm.getOrder(), result);
		ModelAndView mav1 = new ModelAndView("order/registerForm");
		if (result.hasErrors()) return mav1;
		orderForm.getOrder().setExpiryDate(orderForm.convertToFormattedDate(orderForm.getOrder().getExpiryDate()));
		String sAddress = postCode + " " + address1 + " " + address2;
		orderForm.getOrder().setShipAddress(sAddress);
		closet.createDelivery(orderForm.getOrder()); //등록 
		
		Meet meet = closet.findMeetByUserIdAndProductId(orderForm.getOrder().getUserId(), orderForm.getOrder().getProductId());
		meet.setMeetResult(3);
		closet.insertMeet(meet);
		
		ModelAndView mav2 = new ModelAndView("order/detail");
		mav2.addObject("order", orderForm.getOrder());
		status.setComplete();  // remove session
		return mav2;
	}
}
