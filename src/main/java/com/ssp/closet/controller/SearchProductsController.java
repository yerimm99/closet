package com.ssp.closet.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.service.ClosetFacade;

@Controller
@SessionAttributes({"GroupbuyList", "AuctionList", "keyword"})
public class SearchProductsController { 

	private ClosetFacade closet;

	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}

	@RequestMapping("/closet/search.do")
	public String searchProduct(
			) throws Exception {
		return "main/search";
	}

	@RequestMapping("/closet/searchResult.do")
	public ModelAndView searchProduct(HttpServletRequest request,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="page", required=false) String page, ModelMap model
			) throws Exception {
		if (!StringUtils.hasLength(keyword)) {
			return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
		}
		PagedListHolder<Auction> AuctionList = new PagedListHolder<Auction>(this.closet.searchAuctionList(keyword.toLowerCase()));
		PagedListHolder<Groupbuy> GroupbuyList = new PagedListHolder<Groupbuy>(this.closet.searchGroupbuyList(keyword.toLowerCase()));
		AuctionList.setPageSize(8);
		GroupbuyList.setPageSize(8);
		model.put("GroupbuyList", GroupbuyList);
		model.put("AuctionList", AuctionList);
		model.put("keyword", keyword);

		request.getSession().setAttribute("SearchProductsController_productList", model);
		return new ModelAndView("main/SearchProducts", "productList", model);
	}

	@RequestMapping("/closet/searchResult2.do")
	public ModelAndView searchProduct_page(HttpServletRequest request,
			@ModelAttribute("AuctionList") PagedListHolder<Auction> AuctionList,
			@ModelAttribute("GroupbuyList") PagedListHolder<Auction> GroupbuyList,
			@ModelAttribute("keyword") String keyword,
			@RequestParam("pageName") String page,
			ModelMap model
			) throws Exception {

		if ("nextA".equals(page)) {
			AuctionList.nextPage();
		}
		else if ("previousA".equals(page)) {
			AuctionList.previousPage();
		}
		else if ("nextG".equals(page)) {
			GroupbuyList.nextPage();
		}
		else if ("previousG".equals(page)) {
			GroupbuyList.previousPage();
		}
		
		model.put("GroupbuyList", GroupbuyList);
		model.put("AuctionList", AuctionList);
		model.put("keyword", keyword);
		
		request.getSession().setAttribute("SearchProductsController_productList", model);
		return new ModelAndView("main/SearchProducts", "productList", model);
	}
}
