package com.ssp.closet.controller;

import org.springframework.beans.support.PagedListHolder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.service.ClosetFacade;


@Controller
@SessionAttributes()
public class ViewGroupBuyController { 
	private ClosetFacade closet;

	@Autowired
	public void setPetStore(ClosetFacade closet) {
		this.closet = closet;
	}
	
//	@RequestMapping("/closet/groupbuy.do")
//	public String handleRequest(
////			@RequestParam("type") int type,
////			@RequestParam("status") int status,
//			ModelMap model
//			) throws Exception {
//		return "main/groupbuy"; 
//	}
	
//	//공동구매 상품 리스트 보기 -> 이게 되면 경매는 비슷하게 적용하면 될거같아요
	@RequestMapping("/closet/groupbuy.do")
	public String handleRequest2(
//			@RequestParam("type") int type,
//			@RequestParam("status") int status,
			ModelMap model
			) throws Exception {
		PagedListHolder<Groupbuy> productList = new PagedListHolder<Groupbuy>(this.closet.getGroupbuyList());
//		PagedListHolder<Product> productList = new PagedListHolder<Product>();
		productList.setPageSize(20);
		productList.setPageSize(4);
		model.put("productList", productList);
		return "main/groupbuy"; 
	}
}

