package com.ssp.closet.controller;

import com.ssp.closet.dto.LikeMark;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.service.ClosetFacade;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

@Controller
public class LikeController {

	
	@Autowired
	private ClosetFacade closet;

	@Autowired
	public void setClosetFacade(ClosetFacade closet) {
		this.closet = closet;
	}

    @RequestMapping("/like.do")
    public ModelAndView likeMark(HttpServletRequest request,
    		@RequestParam("productId") int productId,
    		ModelMap model) throws Exception {
            
    	UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			Product product = closet.getProduct(productId);
			
			if(closet.cheakLikeMark(product, account) == null) {
				LikeMark like = new LikeMark();
				like.setAccount(account);
				like.setMark(1);
				like.setProduct(product);
				
				closet.createLike(like);
			}
			else {
				closet.deleteLike(product, account);
				}
			model.addAttribute("product", product);
			
			if(product.getDTYPE().equals("Groupbuy")) {
				return new ModelAndView("/groupbuy/detail", model);
			} else {
				return new ModelAndView("/auction/detail", model);
			}
		}
		else {
			ModelAndView mav = new ModelAndView("/account/SignonForm");
 	        return mav;
		}
    }
    
    @RequestMapping("/like/auctionList.do")
    public ModelAndView auctionLikeMarkList(HttpServletRequest request,
    		ModelMap model) throws Exception {
	    	UserSession userSession = 
					(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
			if (userSession != null) {
				Account account = closet.getAccount(userSession.getAccount().getUserId());
				
				List<LikeMark> likeMarkList = closet.findLikeMark(account);
				List<Auction> auctionLikeList  = new ArrayList<>();
				
				for (LikeMark product : likeMarkList) {
					if (product.getProduct().getDTYPE().equals("Auction")) {
						auctionLikeList.add(closet.getAuction(product.getProduct().getProductId()));
					}
				}
		    	PagedListHolder<Auction> AuctionList = new PagedListHolder<>(auctionLikeList);
				
				AuctionList.setPageSize(9);
				model.put("productList", AuctionList);
		
			}
			return new ModelAndView("like/auctionList", "productList", model);
    	
    }
    
    @RequestMapping("/like/groupbuyList.do")
    public ModelAndView groupbuyLikeMarkList(HttpServletRequest request,
    		ModelMap model) throws Exception {
	    	UserSession userSession = 
					(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
			if (userSession != null) {
				Account account = closet.getAccount(userSession.getAccount().getUserId());
				
				List <Groupbuy> groupbuyLikeList = new ArrayList<Groupbuy>();
				
				for (LikeMark product : closet.findLikeMark(account)) {
					if (product.getProduct().getDTYPE().equals("Groupbuy")) {
						groupbuyLikeList.add(closet.getGroupbuyDetail(product.getProduct().getProductId()));
					}
				}
				
				PagedListHolder<Groupbuy> GroupbuyList = new PagedListHolder<Groupbuy>(groupbuyLikeList);
				
				GroupbuyList.setPageSize(9);
				model.put("productList", GroupbuyList);
			}
			return new ModelAndView("like/groupbuyList", "productList", model);
    	
    }
}
