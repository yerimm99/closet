package com.ssp.closet.controller;

import com.ssp.closet.service.ClosetFacade;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Groupbuy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RankController {
	
	@Autowired
	private ClosetFacade closet;

	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}

	//옥션좋아요순
	@RequestMapping("/rank/auction/likeList.do")
	public String auctionLikeRankingList(HttpServletRequest request,
			ModelMap model) {
		
		PagedListHolder<Auction> likeList = new PagedListHolder<Auction>(closet.getAuctionSortedByLikeCount());
		
		likeList.setPageSize(10);
		model.put("productList", likeList);
		model.put("DTYPE", "Auction");
	
		return "main/rank";
	}
	
	//옥션랭킹순
	@RequestMapping("/rank/auction/reviewList.do")
	public String auctionReviewRankingList(HttpServletRequest request,
			ModelMap model) {
		
		PagedListHolder<Auction> reviewList = new PagedListHolder<Auction>(closet.getAuctionRankingByReviewRating());
		
		reviewList.setPageSize(10);
		model.put("productList", reviewList);
		model.put("DTYPE", "Auction");
		
		return "main/rank";
	}
	
	@RequestMapping("/rank/groupbuy/likeList.do")
	public String groupbuyLikeRankingList(HttpServletRequest request,
			ModelMap model) {
		
		PagedListHolder<Groupbuy> groupbuyList = new PagedListHolder<Groupbuy>(closet.getGroupbuySortedByLikeCount());
		
		groupbuyList.setPageSize(10);
		model.put("productList", groupbuyList);
		model.put("DTYPE", "Groupbuy");
		
		return "main/rank";
	}
	
	@RequestMapping("/rank/groupbuy/reviewList.do")
	public String groupbuyReviewRankingList(HttpServletRequest request,
			ModelMap model) {
		
		PagedListHolder<Groupbuy> groupbuyList = new PagedListHolder<Groupbuy>(closet.getGroupbuyRankingByReviewRating());
		
		groupbuyList.setPageSize(10);
		model.put("productList", groupbuyList);
		model.put("DTYPE", "Groupbuy");
		
		return "main/rank";
	}
	
	
}
