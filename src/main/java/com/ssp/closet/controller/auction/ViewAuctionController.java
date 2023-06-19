package com.ssp.closet.controller.auction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Bid;
import com.ssp.closet.dto.PagingVO;
import com.ssp.closet.service.ClosetFacade;
import com.ssp.closet.service.PagingService;

@Controller
@SessionAttributes()
public class ViewAuctionController {
		private ClosetFacade closet;
		
		@Autowired
		public void setCloset(ClosetFacade closet) {
			this.closet = closet;
		}
		
		@Autowired
		private PagingService pagingService;
		
		//경매 상품 리스트 보기
		@RequestMapping("/closet/auction.do")
		public String handleRequest1(@PageableDefault(size = 2, sort = "status", direction = Direction.DESC) Pageable pageable,
				ModelMap model) {//@PageableDefault -> size: 한페이지에 게시물수 / sort: 정렬 기준 / direction: 정렬 방법
			Page<Auction> pageList = closet.getAuctionList(pageable);//경매게시글에 대한 페이징 객체
			List<Auction> productList = pageList.getContent();//페이징 객체에 있는 내용물들
			//게시판 리스트에서 페이지 숫자 및 화살표 표시해주기 위한 정보를 담은 객체
			PagingVO paging = pagingService.pagingInfoA(pageList);
			
			model.put("productList", productList);
			model.put("paging", paging);
			model.put("preview", paging.getPreviousPageGroupOfPage());
			model.put("next", paging.getNextPageGroupOfPage());

			return "main/auction";
		}
		
		//경매 상품 리스트 선택보기
		@RequestMapping("/auction/list.do")
		public String handleRequest2(@PageableDefault(size = 2, sort = "status", direction = Direction.DESC) Pageable pageable,
				@RequestParam("categoryId") String categoryId,
				ModelMap model
				) throws Exception {
			Page<Auction> pageList;
			if(categoryId.equals("전체")) {
				pageList = closet.getAuctionList(pageable);
			}
			else {
				pageList =closet.getAuctionByCategoryId(categoryId, pageable);
			}
			List<Auction> productList = pageList.getContent();//페이징 객체에 있는 내용물들
			PagingVO paging = pagingService.pagingInfoA(pageList);
			
			model.put("categoryId", categoryId);
			model.put("productList", productList);
			model.put("paging", paging);
			model.put("preview", paging.getPreviousPageGroupOfPage());
			model.put("next", paging.getNextPageGroupOfPage());
			return "main/auction"; 
		}
		
		//내가 판매 중인 경매 상품 리스트 보기
		@RequestMapping("/myPage/sellAuction.do")
		public String handleRequest3(@PageableDefault(size = 2, sort = "status", direction = Direction.DESC) Pageable pageable,
				HttpServletRequest request,
				ModelMap model) throws Exception {
			UserSession userSession = 
					(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
			if (userSession != null) {
				Account account = closet.getAccount(userSession.getAccount().getUserId());

				Page<Auction> pageList = closet.findSellAuctionByAccount(account, pageable);
				List<Auction> productList = pageList.getContent();//페이징 객체에 있는 내용물들
				PagingVO paging = pagingService.pagingInfoA(pageList);
				
				model.put("productList", productList);
				model.put("paging", paging);
				model.put("preview", paging.getPreviousPageGroupOfPage());
				model.put("next", paging.getNextPageGroupOfPage());
			}
			return "auction/sellResultList";
		}
		
		//내가 구매 신청한 경매 상품 리스트 보기
		@RequestMapping("/myPage/buyAuction.do")
		public String handleRequest4(@PageableDefault(size = 2, sort = "status", direction = Direction.DESC) Pageable pageable,
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
				
				final int start = (int)pageable.getOffset();
				final int end = Math.min((start + pageable.getPageSize()), productAuctions.size());
				Page<Auction> pageList = new PageImpl<>(productAuctions.subList(start, end), pageable, productAuctions.size());
				List<Auction> productList = pageList.getContent();//페이징 객체에 있는 내용물들
				PagingVO paging = pagingService.pagingInfoA(pageList);
				
				model.put("productList", productList);
				model.put("paging", paging);
				model.put("preview", paging.getPreviousPageGroupOfPage());
				model.put("next", paging.getNextPageGroupOfPage());
				model.put("bidList", bid);
			} 
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
