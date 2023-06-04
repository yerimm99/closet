package com.ssp.closet.controller;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ssp.closet.dto.Product;
import com.ssp.closet.service.ClosetFacade;


@Controller
@SessionAttributes({"productList"})
//@RequestMapping("/view/productsList.do")
public class ViewProductListController { 
	private ClosetFacade closet;

	@Autowired
	public void setPetStore(ClosetFacade closet) {
		this.closet = closet;
	}

	@RequestMapping("/closet/auction.do")
	public String handleRequest(
			ModelMap model
			) throws Exception {
		PagedListHolder<Product> productList = new PagedListHolder<Product>(this.closet.getProductList());
		productList.setPageSize(4);
		model.put("productList", productList);
		return "auction/list";
	}

//	@RequestMapping("/view/productsList2.do")
//	public String handleRequest2(
//			@RequestParam("page") String page,
//			@ModelAttribute("productList") PagedListHolder<Product> productList,
//			BindingResult result) throws Exception {
//		if (productList == null) {
//			throw new IllegalStateException("Cannot find pre-loaded category and product list");
//		}
//		if ("next".equals(page)) { productList.nextPage(); }
//		else if ("previous".equals(page)) { productList.previousPage(); }
//		return "auction/list";
//	}
}