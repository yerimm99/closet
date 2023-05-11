package com.ssp.closet.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssp.closet.dao.BookmarkDao;
import com.ssp.closet.dto.Bookmark;

public class BookmarkServiceImpl implements BookmarkService {

	@Autowired
	private BookmarkDao bookmarkDao;
	
	public void createMark(Bookmark bookmark) {
		bookmarkDao.createMark(bookmark);
	}
	public void deleteMark(String userId, int productId) {
		bookmarkDao.deleteMark(userId, productId);
	}
}
