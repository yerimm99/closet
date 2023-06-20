package com.ssp.closet.controller.auction;

import java.util.ArrayList;
import org.springframework.beans.support.PagedListHolder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Bid;
import com.ssp.closet.service.ClosetFacade;

@Controller
@SessionAttributes({"productList", "bidList"})
public class ViewAuctionController {
	private ClosetFacade closet;

	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	} 

	//경매 상품 리스트 보기
	@RequestMapping("/closet/auction.do")
	public String handleRequest1(
			ModelMap model) {
		PagedListHolder<Auction> productList = new PagedListHolder<Auction>(closet.getAuctionList());
		productList.setPageSize(4);

		model.put("productList", productList);

		return "main/auction";
	}


	@RequestMapping("/closet/auction2.do")
	public String handleRequest11(
			@ModelAttribute("productList") PagedListHolder<Auction> productList,
			@RequestParam("pageName") String page, 
			ModelMap model) throws Exception {
		if ("next".equals(page)) {
			productList.nextPage();
		}
		else if ("previous".equals(page)) {
			productList.previousPage();
		}
		model.put("productList", productList);
		return "main/auction";
	}

	//경매 상품 리스트 선택보기
	@RequestMapping("/auction/list.do")
	public String handleRequest2(
			@RequestParam("categoryId") String categoryId,
			ModelMap model
			) throws Exception {
		PagedListHolder<Auction> productList;
		if(categoryId.equals("전체")) {
			productList = new PagedListHolder<Auction>(closet.getAuctionList());
		}
		else {
			productList = new PagedListHolder<Auction>(closet.getAuctionByCategoryId(categoryId));
		}
		productList.setPageSize(4);

		model.put("categoryId", categoryId);
		model.put("productList", productList);
		return "main/auction"; 
	}

	//경매 상품 리스트 선택보기
	@RequestMapping("/auction/list2.do")
	public String handleRequest22(
			@RequestParam("categoryId") String categoryId,
			@RequestParam("used") int used,
			ModelMap model
			) throws Exception {
		PagedListHolder<Auction> productList;
		if(categoryId.equals("전체")) {
			productList = new PagedListHolder<Auction>(closet.getAuctionByUsed(used));
		}
		else {
			productList = new PagedListHolder<Auction>(closet.getAuctionByCategoryIdAndUsed(categoryId, used));
		}
		productList.setPageSize(4);

		model.put("categoryId", categoryId);
		model.put("used", used);
		model.put("productList", productList);
		return "main/auction"; 
	}


	//내가 판매 중인 경매 상품 리스트 보기
	@RequestMapping("/myPage/sellAuction.do")
	public String handleRequest3(
			HttpServletRequest request,
			ModelMap model) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			PagedListHolder<Auction> productList = new PagedListHolder<Auction>(closet.findSellAuctionByAccount(account));
			productList.setPageSize(4);
			model.put("productList", productList);
			return "auction/sellResultList";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}

	//내가 판매 중인 경매 상품 리스트 보기
	@RequestMapping("/myPage/sellAuction2.do")
	public String handleRequest33(
			HttpServletRequest request,
			@ModelAttribute("productList") PagedListHolder<Auction> productList,
			@RequestParam("pageName") String page, 
			ModelMap model) throws Exception {
		if ("next".equals(page)) {
			productList.nextPage();
		}
		else if ("previous".equals(page)) {
			productList.previousPage();
		}
		model.put("productList", productList);
		return "auction/sellResultList";
	}

	//내가 구매 신청한 경매 상품 리스트 보기
	@RequestMapping("/myPage/buyAuction.do")
	public String handleRequest4(
			HttpServletRequest request,
			ModelMap model
			) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			List<Bid> bid = closet.getBid(account.getUserId());
			List<Auction> productAuctions = new ArrayList<>();
			for (Bid b : bid) {
				Auction auction = closet.getAuction(b.getProductId());
				if (auction != null) {
					productAuctions.add(auction);
				}
			}
			PagedListHolder<Auction> productList = new PagedListHolder<Auction>(productAuctions);
			productList.setPageSize(4);
			PagedListHolder<Bid> bidList = new PagedListHolder<Bid>(bid);
			bidList.setPageSize(4);

			model.put("productList", productList);
			model.put("bidList", bidList);
		} 
		return "auction/buyResultList";
	}

	//내가 판매 중인 경매 상품 리스트 보기
	@RequestMapping("/myPage/buyAuction2.do")
	public String handleRequest44(
			HttpServletRequest request,
			@ModelAttribute("productList") PagedListHolder<Auction> productList,
			@ModelAttribute("bidList") PagedListHolder<Bid> bidList,
			@RequestParam("pageName") String page, 
			ModelMap model) throws Exception {
		if ("next".equals(page)) {
			productList.nextPage();
		}
		else if ("previous".equals(page)) {
			productList.previousPage();
		}
		model.put("productList", productList);
		model.put("bidList", bidList);
		return "auction/buyResultList";
	}


	@RequestMapping("/auction/detail.do")
	public void detailAuction(
			@RequestParam("productId") int productId,
			ModelMap model) throws Exception {
		Auction product = closet.getAuction(productId);
		model.put("product", product);
	}

	@RequestMapping("/myPage/myAuction.do")
	public String handleRequest5(HttpServletRequest request,
			@RequestParam("productId") int productId) throws Exception {

		Auction auction = closet.getAuction(productId);
		closet.closedAuctionBySupp(auction);

		return "auction/sellResultList";

	}
}
