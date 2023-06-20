package com.ssp.closet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.Product;

public interface ProductDao {

	//공동구매 상품 등록
	void insertGroupBuyProduct(Product product, Groupbuy groupBuy) throws DataAccessException;

	//상품 수정
	void updateProduct(int productId) throws DataAccessException;

	//상품 삭제
	void deleteProduct(int productId) throws DataAccessException;

	//상품 리스트 조회
	List<Product> getProductList() throws DataAccessException;

	//상품 상세보기
	Product getProductDetail(int productId) throws DataAccessException;
	
	//랭킹 코드
//	List<Product> getTopRankingProducts();
}
