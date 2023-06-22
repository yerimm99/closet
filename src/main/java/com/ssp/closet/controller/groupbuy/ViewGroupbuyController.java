package com.ssp.closet.controller.groupbuy;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.support.PagedListHolder;

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
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.LikeMark;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.Product;
import com.ssp.closet.service.ClosetFacade;


@Controller
@SessionAttributes({"productList", "meetList"})
public class ViewGroupbuyController { 
	@Autowired
	private ClosetFacade closet;

	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}

	//공동구매 상품 리스트 보기
	@RequestMapping("/closet/groupbuy.do")
	public String viewAllGroupbuyList(
			ModelMap model) {
		PagedListHolder<Groupbuy> productList = new PagedListHolder<Groupbuy>(closet.getGroupbuyList());//경매게시글에 대한 페이징 객체
		productList.setPageSize(9);
		model.put("productList", productList);
		return "main/groupbuy";
	}

	@RequestMapping("/closet/groupbuy2.do")
	public String viewGroupbuyList_page(
			@ModelAttribute("productList") PagedListHolder<Groupbuy> productList,
			@RequestParam("pageName") String page, 
			ModelMap model) throws Exception {
		if ("next".equals(page)) {
			productList.nextPage();
		}
		else if ("previous".equals(page)) {
			productList.previousPage();
		}
		model.put("productList", productList);
		return "main/groupbuy";
	}

	//공동구매 상품 리스트 선택보기
	@RequestMapping("/groupbuy/list.do")
	public String viewGroupbuyListByCategory(
			@RequestParam("categoryId") String categoryId,
			ModelMap model
			) throws Exception {
		PagedListHolder<Groupbuy> productList;
		if(categoryId.equals("전체")) {
			productList = new PagedListHolder<Groupbuy>(closet.getGroupbuyList());
		}
		else {
			productList = new PagedListHolder<Groupbuy>(closet.getGroupbuyByCategoryId(categoryId));
		}
		productList.setPageSize(9);

		model.put("categoryId", categoryId);
		model.put("productList", productList);

		return "main/groupbuy"; 
	}
	
	
	//내가 판매 중인 공동구매 상품 리스트 보기
	@RequestMapping("/myPage/sellGroupbuy.do")
	public String viewGroupbuyListMySell(
			HttpServletRequest request,
			ModelMap model
			) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			PagedListHolder<Groupbuy> productList = new PagedListHolder<Groupbuy>(closet.findSellGroupbuyByAccount(account));
			productList.setPageSize(5);
			model.put("productList", productList);
			return "groupbuy/sellResultList";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}
	
	//내가 판매 중인 공동구매 상품 리스트 보기
	@RequestMapping("/myPage/sellGroupbuy2.do")
	public String viewGroupbuyListMySell_page(
			HttpServletRequest request,
			@ModelAttribute("productList") PagedListHolder<Groupbuy> productList,
			@RequestParam("pageName") String page, 
			ModelMap model) throws Exception {
		if ("next".equals(page)) {
			productList.nextPage();
		}
		else if ("previous".equals(page)) {
			productList.previousPage();
		}
		model.put("productList", productList);
		return "groupbuy/sellResultList";
	}

	//내가 구매 신청한 공동구매 상품 리스트 보기
	@RequestMapping("/myPage/buyGroupbuy.do")
	public String viewGroupbuyListMyBuy(
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
			PagedListHolder<Groupbuy> productList = new PagedListHolder<Groupbuy>(productGroupbuys);
			productList.setPageSize(5);
			PagedListHolder<Meet> meetList = new PagedListHolder<Meet>(meet);
			meetList.setPageSize(5);

			model.put("productList", productList);
			model.put("meetList", meetList);
			return "groupbuy/buyResultList";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}
	
	//내가 판매 중인 공동구매 상품 리스트 보기
	@RequestMapping("/myPage/buyGroupbuy2.do")
	public String viewGroupbuyListMyBuy_page(
			HttpServletRequest request,
			@ModelAttribute("productList") PagedListHolder<Groupbuy> productList,
			@ModelAttribute("meetList") PagedListHolder<Meet> meetList,
			@RequestParam("pageName") String page, 
			ModelMap model) throws Exception {
		if ("next".equals(page)) {
			productList.nextPage();
			meetList.nextPage();
		}
		else if ("previous".equals(page)) {
			productList.previousPage();
			meetList.previousPage();
		}
		model.put("productList", productList);
		model.put("meetList", meetList);
		return "groupbuy/buyResultList";
	}

	@RequestMapping("/groupbuy/detail.do")
	   public void detailGroupbuy(
	         HttpServletRequest request,
	         @RequestParam("productId") int productId,
	         ModelMap model) throws Exception {
	      Groupbuy product = this.closet.getGroupbuyDetail(productId);
	      
	      UserSession userSession = 
	            (UserSession) WebUtils.getSessionAttribute(request, "userSession");
	      String supp = "";
	      int like = -1;
	      if(userSession != null) {
	         supp = userSession.getAccount().getUserId();
	         Product prod = closet.getProduct(productId);
	         LikeMark lm = closet.cheakLikeMark(prod, userSession.getAccount());
	         if(lm != null) {
	        	 like = lm.getMark();
	         }
	      }
	      
	      Meet meet = closet.findMeetByUserIdAndProductId(supp, productId);
	      
	      model.put("like", like);
	      model.put("meet", meet);
	      model.put("supp", supp);
	      model.put("product", product);
	   }
}
