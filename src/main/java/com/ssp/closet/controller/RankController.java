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

	//좋아요순
	@RequestMapping("/closet/best/auction.do")
	public String auctionRankingList(HttpServletRequest request,
			ModelMap model) {
		
		PagedListHolder<Auction> likeList = new PagedListHolder<Auction>(closet.getAuctionSortedByLikeCount());
		
		likeList.setPageSize(10);
		model.put("likeList", likeList);
		
		PagedListHolder<Auction> reviewList = new PagedListHolder<Auction>(closet.getAuctionRankingByReviewRating());
		
		reviewList.setPageSize(10);
		model.put("reviewList", reviewList);
		
		return "rank/auctionList";
	}
	
	@RequestMapping("/closet/best/groupbuy.do")
	public String groupbuyRankingList(HttpServletRequest request,
			ModelMap model) {
		
		PagedListHolder<Groupbuy> groupbuyList = new PagedListHolder<Groupbuy>(closet.getGroupbuySortedByLikeCount());
		
		groupbuyList.setPageSize(10);
		model.put("likeList", groupbuyList);
		
		PagedListHolder<Groupbuy> reviewList = new PagedListHolder<Groupbuy>(closet.getGroupbuyRankingByReviewRating());
		
		reviewList.setPageSize(10);
		model.put("reviewList", reviewList);
		
		return "rank/groupbuyList";
	}
	
	
}
