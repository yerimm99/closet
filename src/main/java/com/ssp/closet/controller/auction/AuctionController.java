package com.ssp.closet.controller.auction;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.service.ClosetFacade;

@Controller
public class AuctionController {
	@Autowired
	private ClosetFacade closet;

	@Autowired
	public void setClosetStore(ClosetFacade closet) {
		this.closet = closet;
	}

	@RequestMapping("/auction/delete.do")
	public String removeAuction(HttpServletRequest request,
			@RequestParam("productId") int productId
			) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			if(closet.countBidByProductId(productId) == 0) {
				closet.deleteAuctionByProductId(productId);
			}
			else {
				return "redirect:/popup/deleteAuction.do";
			}
		}
		else {
			return "redirect:/account/SignonForm.do";
		}
		return "redirect:/closet/mypage.do";
	}

	@RequestMapping("/popup/deleteAuction.do")
	public String showPopup() {
		return "groupbuy/popup";
	}
}
