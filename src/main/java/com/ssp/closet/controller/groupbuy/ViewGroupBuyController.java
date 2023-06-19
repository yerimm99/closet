package com.ssp.closet.controller.groupbuy;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.PagingVO;
import com.ssp.closet.service.ClosetFacade;
import com.ssp.closet.service.PagingService;


@Controller
@SessionAttributes()
public class ViewGroupBuyController { 
	@Autowired
	private ClosetFacade closet;
	
	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}
	
	@Autowired
	private PagingService pagingService;

	//공동구매 상품 리스트 보기
	@RequestMapping("/closet/groupbuy.do")
	public String handleRequest1(@PageableDefault(size = 2, sort = "status", direction = Direction.DESC) Pageable pageable,
			ModelMap model) {//@PageableDefault -> size: 한페이지에 게시물수 / sort: 정렬 기준 / direction: 정렬 방법
		Page<Groupbuy> pageList = closet.getGroupbuyList(pageable);//경매게시글에 대한 페이징 객체
		List<Groupbuy> productList = pageList.getContent();//페이징 객체에 있는 내용물들
		//게시판 리스트에서 페이지 숫자 및 화살표 표시해주기 위한 정보를 담은 객체
		PagingVO paging = pagingService.pagingInfoG(pageList);
		
		model.put("productList", productList);
		model.put("paging", paging);
		model.put("preview", paging.getPreviousPageGroupOfPage());
		model.put("next", paging.getNextPageGroupOfPage());

		return "main/groupbuy";
	}

	//공동구매 상품 리스트 선택보기
	@RequestMapping("/groupbuy/list.do")
	public String handleRequest2(@PageableDefault(size = 2, sort = "status", direction = Direction.DESC) Pageable pageable,
			@RequestParam("categoryId") String categoryId,
			ModelMap model
			) throws Exception {
		Page<Groupbuy> pageList;
		if(categoryId.equals("전체")) {
			pageList = closet.getGroupbuyList(pageable);
		}
		else {
			pageList = closet.getGroupbuyByCategoryId(categoryId, pageable);
		}
		List<Groupbuy> productList = pageList.getContent();//페이징 객체에 있는 내용물들
		PagingVO paging = pagingService.pagingInfoG(pageList);
		
		model.put("categoryId", categoryId);
		model.put("productList", productList);
		model.put("paging", paging);
		model.put("preview", paging.getPreviousPageGroupOfPage());
		model.put("next", paging.getNextPageGroupOfPage());
		
		return "main/groupbuy"; 
	}


	//내가 판매 중인 공동구매 상품 리스트 보기
	@RequestMapping("/myPage/sellGroupbuy.do")
	public String handleRequest3(@PageableDefault(size = 2, sort = "status", direction = Direction.DESC) Pageable pageable,
			HttpServletRequest request,
			ModelMap model
			) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			Page<Groupbuy> pageList = closet.findSellGroupbuyByAccount(account, pageable);
			List<Groupbuy> productList = pageList.getContent();//페이징 객체에 있는 내용물들
			PagingVO paging = pagingService.pagingInfoG(pageList);
			
			model.put("productList", productList);
			model.put("paging", paging);
			model.put("preview", paging.getPreviousPageGroupOfPage());
			model.put("next", paging.getNextPageGroupOfPage());
			return "groupbuy/sellResultList";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}

	//내가 구매 신청한 공동구매 상품 리스트 보기
	@RequestMapping("/myPage/buyGroupbuy.do")
	public String handleRequest4(@PageableDefault(size = 2, sort = "status", direction = Direction.DESC) Pageable pageable,
			HttpServletRequest request,
			ModelMap model
			) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			List<Meet> meet = this.closet.findMeetByUserId(account.getUserId());
			List<Groupbuy> productGroupbuys = new ArrayList<>();
			for (Meet m : meet) {
				Groupbuy groupbuy = this.closet.findBuyGroupbuyByProductId(m.getProductId());
				if (groupbuy != null) {
					productGroupbuys.add(groupbuy);
				}
			}
			final int start = (int)pageable.getOffset();
			final int end = Math.min((start + pageable.getPageSize()), productGroupbuys.size());
			Page<Groupbuy> pageList = new PageImpl<>(productGroupbuys.subList(start, end), pageable, productGroupbuys.size());
			List<Groupbuy> productList = pageList.getContent();//페이징 객체에 있는 내용물들
			PagingVO paging = pagingService.pagingInfoG(pageList);
			
			model.put("productList", productList);
			model.put("paging", paging);
			model.put("preview", paging.getPreviousPageGroupOfPage());
			model.put("next", paging.getNextPageGroupOfPage());
			model.put("meetList", meet);
			return "groupbuy/buyResultList";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}

	@RequestMapping("/groupbuy/detail.do")
	public void detailGroupbuy(
			@RequestParam("productId") int productId,
			ModelMap model) throws Exception {
		Groupbuy product = this.closet.getGroupbuyDetail(productId);
		model.put("product", product);
	}
}
