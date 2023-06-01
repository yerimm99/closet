package com.ssp.closet.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dao.BookmarkDao;
import com.ssp.closet.dao.mybatis.mapper.BookmarkMapper;
import com.ssp.closet.dto.Bookmark;

@Repository
public class MybatisBookmarkDao implements BookmarkDao {
	@Autowired
	protected BookmarkMapper bookmarkMapper;
	
	public void createMark(Bookmark bookmark) {
		bookmarkMapper.createMark(bookmark);
	}
	
	public void deleteMark(String userId, int productId) {
		bookmarkMapper.deleteMark(userId, productId);
	}
}
