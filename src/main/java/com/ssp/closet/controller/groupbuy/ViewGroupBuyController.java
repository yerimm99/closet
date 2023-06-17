package com.ssp.closet.controller.groupbuy;

import org.springframework.beans.support.PagedListHolder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.service.ClosetFacade;


@Controller
@SessionAttributes()
public class ViewGroupBuyController { 
	private ClosetFacade closet;

	@Autowired
	public void setPetStore(ClosetFacade closet) {
		this.closet = closet;
	}

	//	@RequestMapping("/closet/groupbuy.do")
	//	public String handleRequest(
	////			@RequestParam("type") int type,
	////			@RequestParam("status") int status,
	//			ModelMap model
	//			) throws Exception {
	//		return "main/groupbuy"; 
	//	}

	//	//공동구매 상품 리스트 보기
	@RequestMapping("/closet/groupbuy.do")
	public String handleRequest1(
			//			@RequestParam("type") int type,
			//			@RequestParam("status") int status,
			ModelMap model
			) throws Exception {
		PagedListHolder<Groupbuy> productList = new PagedListHolder<Groupbuy>(this.closet.getGroupbuyList());
		//		PagedListHolder<Product> productList = new PagedListHolder<Product>();
		productList.setPageSize(20);
		productList.setPageSize(4);
		model.put("productList", productList);
		return "main/groupbuy"; 
	}

	//공동구매 상품 리스트 선택보기
	@RequestMapping("/groupbuy/list.do")
	public String handleRequest2(
			@RequestParam("categoryId") String categoryId,
			ModelMap model
			) throws Exception {
		PagedListHolder<Groupbuy> productList;
		if(categoryId.equals("전체")) {
			productList = new PagedListHolder<Groupbuy>(this.closet.getGroupbuyList());
		}
		else {
			productList = new PagedListHolder<Groupbuy>(this.closet.getGroupbuyByCategoryId(categoryId));
		}
		productList.setPageSize(20);
		productList.setPageSize(4);
		model.put("productList", productList);
		return "main/groupbuy"; 
	}


	@RequestMapping("/groupbuy/detail.do")
	public void detailGroupbuy(
			@RequestParam("productId") int productId,
			ModelMap model) throws Exception {
		Groupbuy product = this.closet.getGroupbuyDetail(productId);
		model.put("product", product);
	}
}