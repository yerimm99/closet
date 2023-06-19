package com.ssp.closet.controller.groupbuy;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.controller.auction.AuctionForm;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Bid;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.service.AuctionFormValidator;
import com.ssp.closet.service.ClosetFacade;
import com.ssp.closet.service.GroupbuyFormValidator;


@Controller
@SessionAttributes("groupbuyForm")
public class GroupbuyFormController {

	@Autowired
	private ClosetFacade closet;
	
	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}

	@ModelAttribute("groupbuyForm")
	public GroupbuyForm createGroupbuyForm() {
		return new GroupbuyForm();
	}
	
	@Autowired
	private GroupbuyFormValidator validator;
	public void setValidator(GroupbuyFormValidator validator) {
		this.validator = validator;
	}

//	@RequestMapping(method = RequestMethod.GET)
//	public String showForm() {
//		return formViewName;
//	}

	@ModelAttribute("categories")
	public List<String> referenceData2() {
		ArrayList<String> categories = new ArrayList<String>();
		categories.add("신발");
		categories.add("아우터");
		categories.add("상의");
		categories.add("하의");
		categories.add("가방");
		categories.add("지갑");
		categories.add("시계");
		categories.add("패션잡화");
		return categories;			
	}

	@RequestMapping("/groupbuy/registerForm.do")  //groupbuy 등록 
	public String initNewGroupbuy(HttpServletRequest request,
			@ModelAttribute("groupbuyForm") GroupbuyForm groupbuyForm
			) throws ModelAndViewDefiningException {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			groupbuyForm.getGroupbuy().initGroupbuy(account);
			groupbuyForm.setNewGroupbuy(true);
			return "groupbuy/registerForm";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}
	
	@RequestMapping("/groupbuy/update.do")  //groupbuy 수정
	public String editGroupbuy(HttpServletRequest request,
			@RequestParam("productId") int productId,
			@ModelAttribute("groupbuyForm") GroupbuyForm groupbuyForm
			) throws ModelAndViewDefiningException {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			Groupbuy existingGroupbuy = closet.getGroupbuyDetail(productId);
			groupbuyForm.getGroupbuy().initGroupbuy(account);
			if (existingGroupbuy != null) {
				groupbuyForm.setGroupbuy(existingGroupbuy);
				groupbuyForm.setNewGroupbuy(false);
			}
			return "groupbuy/registerForm";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}
	
	
	@RequestMapping("/groupbuy/confirmGroupbuy.do")
	protected ModelAndView confirmGroupbuy( //auction 등록 확인 
			@ModelAttribute("groupbuyForm") GroupbuyForm groupbuyForm, 
			SessionStatus status, BindingResult result) {

		validator.validateGroupbuyForm(groupbuyForm.getGroupbuy(), result);
		ModelAndView mav1 = new ModelAndView("groupbuy/registerForm");
		if (result.hasErrors()) return mav1;
		
		closet.insertGroupbuy(groupbuyForm.getGroupbuy()); //등록 
		ModelAndView mav2 = new ModelAndView("groupbuy/detail");
		mav2.addObject("product", groupbuyForm.getGroupbuy());
		status.setComplete();  // remove session
		return mav2;
	}
}
