package com.ssp.closet.service;

import com.ssp.closet.dto.Bookmark;

public interface BookmarkService {
	
	void createMark(Bookmark bookmark);
	void deleteMark(String userId, int productId);
}
