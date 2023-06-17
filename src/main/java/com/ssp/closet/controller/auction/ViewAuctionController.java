package com.ssp.closet.controller.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Groupbuy;
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
			PagedListHolder<Auction> productList = new PagedListHolder<Auction>(this.closet.getAuctionList());
			productList.setPageSize(20);
			productList.setPage(5);
			model.put("productList", productList);
			return "/main/auction";
		}
		
		@RequestMapping("/auction/list.do")
		public String handleRequest2(
				@RequestParam("categoryId") String categoryId,
				ModelMap model
				) throws Exception {
			PagedListHolder<Auction> productList;
			if(categoryId.equals("전체")) {
				productList = new PagedListHolder<Auction>(this.closet.getAuctionList());
			}
			else {
				productList = new PagedListHolder<Auction>(this.closet.getAuctionByCategoryId(categoryId));
			}
			productList.setPageSize(20);
			productList.setPageSize(4);
			model.put("productList", productList);
			return "main/auction"; 
		}
		
		@RequestMapping("/auction/detail.do")
		public void detailAuction(
				@RequestParam("productId") int productId,
				ModelMap model) throws Exception {
			Auction product = this.closet.getAuction(productId);
			model.put("product", product);
		}
}
