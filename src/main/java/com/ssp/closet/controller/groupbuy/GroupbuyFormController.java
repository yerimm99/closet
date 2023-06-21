package com.ssp.closet.controller.groupbuy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Groupbuy;
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
	protected ModelAndView confirmGroupbuy(
			@ModelAttribute("groupbuyForm") GroupbuyForm groupbuyForm, 
			@RequestParam("files") List<MultipartFile> files,
			SessionStatus status, BindingResult result) {

		validator.validateGroupbuyForm(groupbuyForm.getGroupbuy(), result);
		ModelAndView mav1 = new ModelAndView("groupbuy/registerForm");
		if (result.hasErrors()) return mav1;
		//이미지가 저장되는 경로
		String absolutePath = System.getProperty("user.dir")+
				"/src/main/webapp/upload/";
		File nfile = new File(absolutePath);

		//경로에 폴더가 존재 안해?
		if(!nfile.exists()) {
			//그럼 그경로로 모든 폴더 만들어
			boolean makeDirStatus = nfile.mkdirs();
			if(!makeDirStatus) System.out.println("디렉토리 생성실패");
		}
		List<String> picturePaths = new ArrayList<>(files.size()); // 파일 경로 리스트 초기화
		List<String> filee = new ArrayList<>();

		// 최소 2개 이상의 이미지를 업로드했는지 확인
		if (files.size() < 2) {
			ModelAndView mav = new ModelAndView("groupbuy/registerForm");
			return mav;
		}

		// 업로드된 파일 처리
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				try {
					// 파일 저장
					String fileName = file.getOriginalFilename();
					String filePath = absolutePath + fileName;
					file.transferTo(new File(filePath));

					// 파일 경로 저장
					picturePaths.add(filePath);
					filee.add(fileName);

				} catch (IOException e) {
					// 파일 저장 중 오류 발생
					e.printStackTrace();
					// 오류 처리
					// ...
					ModelAndView mav = new ModelAndView("index");
					return mav;
				}
			}
		}

		// 파일 경로들을 Product 엔티티의 picture1, picture2, picture3, picture4에 할당
		Groupbuy product = groupbuyForm.getGroupbuy();

		if (picturePaths.size() >= 2) {
			product.setPicture1(filee.get(0));
			product.setPicture2(filee.get(1));
		}
		product.setPicture3(picturePaths.size() >= 3 ? filee.get(2) : null);
		product.setPicture4(picturePaths.size() >= 4 ? filee.get(3) : null);

		closet.insertGroupbuy(product); //등록 
		ModelAndView mav2 = new ModelAndView("groupbuy/detail");
		String supp = product.getAccount().getUserId();
		mav2.addObject("supp", supp);
		mav2.addObject("product", groupbuyForm.getGroupbuy());
		status.setComplete();  // remove session
		return mav2;
	}

	@RequestMapping("/groupbuy/delete.do")
	public String removeGroupbuy(HttpServletRequest request,
			@RequestParam("productId") int productId
			) throws Exception {
		closet.deleteGroupbuyByProductId(productId);
		return "redirect:/myPage/sellGroupbuy.do";
	}
}
