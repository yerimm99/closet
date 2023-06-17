package com.ssp.closet.controller.groupbuy;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.service.ClosetFacade;


@Controller
@SessionAttributes("groupbuyForm")
public class GroupbuyFormController {

	@Autowired
	private ClosetFacade closet;
	
	@Autowired
	public void setClosetStore(ClosetFacade closet) {
		this.closet = closet;
	}

	@ModelAttribute("groupbuyForm")
	public GroupbuyForm createGroupbuyForm() {
		return new GroupbuyForm();
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
			return "groupbuy/registerForm";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}
	
	 // 수정 폼 만들어주시면 추가
	
	
	
	@RequestMapping("/groupbuy/confirmGroupbuy.do")
	public String confirmGroupbuy( //groupbuy 등록 확인 
			@ModelAttribute("groupbuyForm") GroupbuyForm groupbuyForm, 
			SessionStatus status,
			ModelMap model
			) throws Exception{
		closet.insertGroupbuy(groupbuyForm.getGroupbuy()); //등록 
		status.setComplete();  // remove session
		PagedListHolder<Groupbuy> productList = new PagedListHolder<Groupbuy>(this.closet.getGroupbuyList());
		productList.setPageSize(20);
		productList.setPageSize(4);
		model.put("productList", productList);
		return "main/groupbuy"; 
	}
}
