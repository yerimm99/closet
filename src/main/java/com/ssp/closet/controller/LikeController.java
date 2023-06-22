package com.ssp.closet.controller;

import com.ssp.closet.dto.LikeMark;
import com.ssp.closet.dto.Meet;
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
@SessionAttributes("productList")
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
			String supp = "";
			supp = userSession.getAccount().getUserId();
			int like;
			if(closet.cheakLikeMark(product, account) == null) {
				LikeMark likes = new LikeMark();
				likes.setAccount(account);
				likes.setMark(1);
				likes.setProduct(product);
				like = 1;
				
				closet.createLike(likes);
			}
			else {
				closet.deleteLike(product, account);
				like = -1;
				}
			model.addAttribute("product", product);
			model.addAttribute("like", like);
		    model.addAttribute("supp", supp);
			if(product.getDTYPE().equals("Groupbuy")) {
				Meet meet = closet.findMeetByUserIdAndProductId(supp, productId);
				model.put("meet", meet);
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

	@RequestMapping("/like/delete.do")
	public ModelAndView likeMark_delete(HttpServletRequest request,
			@RequestParam("productId") int productId) throws Exception {

		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		

		Account account = closet.getAccount(userSession.getAccount().getUserId());
		Product product = closet.getProduct(productId);

		closet.deleteLike(product, account);

		if(product.getDTYPE().equals("Groupbuy")) {
			return new ModelAndView("redirect:/like/groupbuyList.do");
		} else {
			return new ModelAndView("redirect:/like/auctionList.do");
		}
	}

	@RequestMapping("/like/auctionList.do")
	public ModelAndView auctionLikeMarkList(HttpServletRequest request) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		

		Account account = closet.getAccount(userSession.getAccount().getUserId());

		List<LikeMark> likeMarkList = closet.findLikeMark(account);
		List<Auction> auctionLikeList  = new ArrayList<>();

		for (LikeMark product : likeMarkList) {
			if (product.getProduct().getDTYPE().equals("Auction")) {
				auctionLikeList.add(closet.getAuction(product.getProduct().getProductId()));
			}
		}
		PagedListHolder<Auction> AuctionList = new PagedListHolder<>(auctionLikeList);

		if(AuctionList != null) {
			System.out.println();
			System.out.println("리스트 있음");
			System.out.println();
		}

		AuctionList.setPageSize(4);
		ModelAndView mav2 = new ModelAndView("like/auctionList");
		mav2.addObject("productList", AuctionList);
		return mav2;
	}

	@RequestMapping("/like/auctionList2.do")
	public ModelAndView auctionLikeMarkList_page(@ModelAttribute("productList") PagedListHolder<Auction> productList,
			@RequestParam("pageName") String page, 
			ModelMap model) throws Exception {	

		if ("next".equals(page)) {
			productList.nextPage();
		}
		else if ("previous".equals(page)) {
			productList.previousPage();
		}
		ModelAndView mav2 = new ModelAndView("like/auctionList");
		mav2.addObject("productList", productList);
		return mav2;
	}

	@RequestMapping("/like/groupbuyList.do")
	public ModelAndView groupbuyLikeMarkList(HttpServletRequest request) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		

		Account account = closet.getAccount(userSession.getAccount().getUserId());

		List <Groupbuy> groupbuyLikeList = new ArrayList<Groupbuy>();

		for (LikeMark product : closet.findLikeMark(account)) {
			if (product.getProduct().getDTYPE().equals("Groupbuy")) {
				groupbuyLikeList.add(closet.getGroupbuyDetail(product.getProduct().getProductId()));
			}
		}

		PagedListHolder<Groupbuy> GroupbuyList = new PagedListHolder<Groupbuy>(groupbuyLikeList);

		GroupbuyList.setPageSize(4);
		ModelAndView mav2 = new ModelAndView("like/groupbuyList");
		mav2.addObject("productList", GroupbuyList);
		return mav2;
	}

	@RequestMapping("/like/groupbuyList2.do")
	public ModelAndView groupbuyLikeMarkList_page(@ModelAttribute("productList") PagedListHolder<Groupbuy> productList,
			@RequestParam("pageName") String page, 
			ModelMap model) throws Exception {	

		if ("next".equals(page)) {
			productList.nextPage();
		}
		else if ("previous".equals(page)) {
			productList.previousPage();
		}
		ModelAndView mav2 = new ModelAndView("like/groupbuyList");
		mav2.addObject("productList", productList);
		return mav2;
	}

}
