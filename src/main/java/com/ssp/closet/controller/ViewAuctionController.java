package com.ssp.closet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ssp.closet.dto.Auction;
import com.ssp.closet.service.ClosetFacade;

@Controller
@SessionAttributes()
public class ViewAuctionController {
		private ClosetFacade closet;
		
		@Autowired
		public void setCloset(ClosetFacade closet) {
			this.closet = closet;
		}
		
		@RequestMapping("/closet/auction.do")
		public String handleRequest(
				ModelMap model
				) throws Exception {
			PagedListHolder<Auction> productList = new PagedListHolder<Auction>();
			productList.setPageSize(20);
			productList.setPage(5);
			model.put("productList", productList);
			return "/main/auction";
		}

}
