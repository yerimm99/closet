package com.ssp.closet.controller.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
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
	
	@RequestMapping("/order/registerForm.do")  //order 등록 
	public String initNewOrder(HttpServletRequest request,
			@ModelAttribute("orderForm") OrderForm orderForm
			) throws ModelAndViewDefiningException {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			orderForm.getOrder().initOrder(account);
			return "order/registerForm";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}
}