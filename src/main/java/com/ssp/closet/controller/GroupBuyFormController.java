package com.ssp.closet.controller;

//https://github.com/cspark0/jpetstore/blob/boot-rest/src/main/java/com/example/jpetstore/controller/AccountFormController.java
//위 코드 참고해서 get/post 로 insert/update 구현 필요


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.ssp.closet.dto.Account;
import com.ssp.closet.service.ClosetFacade;

@Controller
@SessionAttributes({"groupBuyForm"})
public class GroupBuyFormController {
	@Autowired
	private ClosetFacade closet;

	@ModelAttribute("groupBuyForm")
	public GroupBuyForm createGroupBuyForm() {
		return new GroupBuyForm();
	}

	@RequestMapping("/shop/newGroupBuyProduct.do")
	public String initNewGroupBuyProduct(HttpServletRequest request,
			@ModelAttribute("groupBuyForm") GroupBuyForm groupBuyForm
			) throws ModelAndViewDefiningException {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			groupBuyForm.getProduct().initGroupBuy(account);
			return "NewGroupBuyForm";	
	}

	@RequestMapping("/shop/editGroupBuyProduct.do")
	public String initEditGroupBuyProduct(HttpServletRequest request,
			@ModelAttribute("groupBuyForm") GroupBuyForm groupBuyForm
			) throws ModelAndViewDefiningException {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			groupBuyForm.getProduct().initGroupBuy(account);
			return "EditGroupBuyForm";	
	}
}