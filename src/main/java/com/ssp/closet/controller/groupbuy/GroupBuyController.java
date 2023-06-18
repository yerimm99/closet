package com.ssp.closet.controller.groupbuy;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.service.ClosetFacade;


@Controller
public class GroupBuyController {
	@Autowired
	private ClosetFacade closet;

	@Autowired
	public void setClosetStore(ClosetFacade closet) {
		this.closet = closet;
	}

	@RequestMapping("/groupbuy/delete.do")
	public String removeGroupbuy(HttpServletRequest request,
			@RequestParam("productId") int productId
			) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Groupbuy groupbuy = closet.getGroupbuyDetail(productId);
			if(groupbuy.getPeopleSum() == 0) {
				closet.deleteGroupbuyByProductId(productId);
			}
			else {
				return "redirect:/popup/deleteGroupbuy.do";
			}
		}
		else {
			return "redirect:/account/SignonForm.do";
		}
		return "redirect:/closet/mypage.do";
	}
	
	@RequestMapping("/popup/deleteGroupbuy.do")
	public String showPopup() {
	    return "groupbuy/popup";
	}
} 
