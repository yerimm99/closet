package com.ssp.closet.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.service.ClosetFacade;

@Controller
public class SearchProductsController { 

	private ClosetFacade closet;

	@Autowired
	public void setPetStore(ClosetFacade closet) {
		this.closet = closet;
	}

	@RequestMapping("/closet/search.do")
	public String searchProduct(
		) throws Exception {
		return "main/search";
	}
	
	@RequestMapping("/closet/searchResult.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="page", required=false) String page, ModelMap model
			) throws Exception {
		if (keyword != null) {
			if (!StringUtils.hasLength(keyword)) {
				return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
			}
			PagedListHolder<Auction> AuctionList = new PagedListHolder<Auction>(this.closet.searchAuctionList(keyword.toLowerCase()));
			PagedListHolder<Groupbuy> GroupbuyList = new PagedListHolder<Groupbuy>(this.closet.searchGroupbuyList(keyword.toLowerCase()));
			AuctionList.setPageSize(6);
			GroupbuyList.setPageSize(6);
			model.put("GroupbuyList", GroupbuyList);
			model.put("AuctionList", AuctionList);
			model.put("keyword", keyword);
			
			request.getSession().setAttribute("SearchProductsController_productList", model);
			return new ModelAndView("main/SearchProducts", "productList", model);
		}
		else {
			@SuppressWarnings("unchecked")
			PagedListHolder<Auction> AuctionList = (PagedListHolder<Auction>)request.getSession().getAttribute("SearchProductsController_AuctionList");
			PagedListHolder<Groupbuy> GroupbuyList = (PagedListHolder<Groupbuy>)request.getSession().getAttribute("SearchProductsController_GroupbuyList");
			model.put("GroupbuyList", GroupbuyList);
			model.put("AuctionList", AuctionList);
			
			if (AuctionList == null || GroupbuyList == null) {
				return new ModelAndView("/main/search", "message", "Your session has timed out. Please start over again.");
			}
			if ("next".equals(page)) {
				AuctionList.nextPage();
			}
			else if ("previous".equals(page)) {
				AuctionList.previousPage();
			}
			return new ModelAndView("SearchProducts", "productList", model);
		}
	}
}
