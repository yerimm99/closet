package com.ssp.closet.dao;

import com.ssp.closet.dto.Bookmark;

public interface BookmarkDao {
	
	void createMark(Bookmark bookmark);
	
	void deleteMark(String userId, int productId);
}
