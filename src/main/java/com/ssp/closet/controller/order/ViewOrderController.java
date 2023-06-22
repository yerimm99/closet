package com.ssp.closet.controller.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Delivery;
import com.ssp.closet.service.ClosetFacade;

@Controller
@SessionAttributes("orderList")
public class ViewOrderController {
	@Autowired
	private ClosetFacade closet;

	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}

	@RequestMapping("/myPage/myOrderList.do")
	public String viewMyOrderList(HttpServletRequest request,
			ModelMap model
			) throws Exception {

		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			PagedListHolder<Delivery> orderList = new PagedListHolder<Delivery>(closet.getOrderList(account.getUserId()));
			orderList.setPageSize(3);
			model.put("orderList", orderList);
			return "order/list";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}
	
	@RequestMapping("/myPage/myOrderList2.do")
	public String viewMyOrderList_page(
			@ModelAttribute("orderList") PagedListHolder<Delivery> orderList,
			@RequestParam("pageName") String page, 
			ModelMap model) throws Exception {
		if ("next".equals(page)) {
			orderList.nextPage();
		}
		else if ("previous".equals(page)) {
			orderList.previousPage();
		}
		model.put("productList", orderList);
		return "order/list";
	}
	
	@RequestMapping("/order/detail.do")
	public void detailOrder(HttpServletRequest request,
			ModelMap model,
			@RequestParam("productId") int productId
			) throws Exception {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");	
		Account account = closet.getAccount(userSession.getAccount().getUserId());
		
		Delivery order = closet.getOrderByUserIdAndProductId(account.getUserId(), productId);
		model.put("order", order);
	}
}
