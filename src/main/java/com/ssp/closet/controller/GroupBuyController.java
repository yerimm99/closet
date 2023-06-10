//package com.ssp.closet.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.ssp.closet.service.ClosetFacade;
//
////https://github.com/cspark0/jpetstore/blob/boot-rest/src/main/java/com/example/jpetstore/controller/AccountFormController.java
////위 코드 참고해서 get/post 로 insert/update 구현 필요
//
//
//
//@Controller
//@RequestMapping({"/groupbuy/registerForm.do", "/groupbuy/editGroupbuy.do"})
//public class GroupBuyController {
//
//	@Value("groupbuy/registerForm")
//	private String formViewName;
//	@Value("main/groupbuy")
//	private String successViewName;
//	@Autowired
//	private ClosetFacade closet;
//
//	@ModelAttribute("groupbuyForm")
//	public GroupbuyForm createGroupbuyForm() {
//		return new GroupbuyForm();
//	}
//
//	@RequestMapping(method = RequestMethod.GET)
//	public String showForm() {
//		return formViewName;
//	}
//
//	@ModelAttribute("categories")
//	public List<String> referenceData2() {
//		ArrayList<String> categories = new ArrayList<String>();
//		categories.add("신발");
//		categories.add("아우터");
//		categories.add("상의");
//		categories.add("하의");
//		categories.add("가방");
//		categories.add("지갑");
//		categories.add("시계");
//		categories.add("패션잡화");
//		return categories;			
//	}
//
//	@RequestMapping(method = RequestMethod.POST)
//	public String onSubmit( //groupbuy 등록 
//			HttpServletRequest request, //HttpSession session, 세션 사용? 
//			@ModelAttribute("groupbuyForm") GroupbuyForm groupbuyForm,
//			BindingResult result) throws Exception {
//		closet.insertGroupbuy(groupbuyForm.getGroupbuy()); //등록 
//		return successViewName;
//	}
//}
