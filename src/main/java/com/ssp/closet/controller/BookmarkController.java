package com.ssp.closet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssp.closet.dto.Bookmark;
import com.ssp.closet.service.BookmarkService;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController {
	
	@Autowired
	BookmarkService bookmarkService;
	@Autowired
	Bookmark bookmark;
	
	@RequestMapping("/create")
	public String createMark(
			@RequestParam(value = "userId") String userId,
			@RequestParam(value = "productId") int productId) {
		bookmark = new Bookmark(userId, productId);
		bookmarkService.createMark(bookmark);
		return ""; //그전에 있던 페이지
	}
	
	@RequestMapping("/delete")
	public String deleteMark(
			@RequestParam(value = "userId") String userId,
			@RequestParam(value = "productId") int productId) {
		bookmarkService.deleteMark(userId, productId);
		return ""; //그 전에 있던 페이지
	}
}
