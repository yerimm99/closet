package com.ssp.closet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssp.closet.dto.Auction;
import com.ssp.closet.service.ClosetFacade;


@Controller
public class ViewIndexController {
	private ClosetFacade closet;
	
	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}
	
	@RequestMapping("/closet/index.do")
	public String handleRequest(HttpServletRequest request,
			ModelMap model
			) throws Exception {
		//List<Auction> productList = closet.findTop4AuctionOrderByRegisterDate();
		//model.put("productList", productList);
		return "/index";
	}
}
