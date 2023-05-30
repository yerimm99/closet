package com.ssp.closet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ssp.closet.service.ClosetFacade;

@Controller
@SessionAttributes("userSession")
public class ListOrdersController {
	private ClosetFacade closetStore;

	@Autowired
	public void setPetStore(ClosetFacade closetStore) {
		this.closetStore = closetStore;
	}

	@RequestMapping("/shop/listBuyOrders.do")
	public ModelAndView handleRequest1(
		@ModelAttribute("userSession") UserSession userSession
		) throws Exception {
		String userId = userSession.getAccount().getUserId();
		return new ModelAndView("ListOrders", "orderList", 
				closetStore.getBuyList(userId));
	}

	@RequestMapping("/shop/listSellOrders.do")
	public ModelAndView handleRequest2(
		@ModelAttribute("userSession") UserSession userSession
		) throws Exception {
		String userId = userSession.getAccount().getUserId();
		return new ModelAndView("ListOrders", "orderList", 
				closetStore.getSellList(userId));
	}
}