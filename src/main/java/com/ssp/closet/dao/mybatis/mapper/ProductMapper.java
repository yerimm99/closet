package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import com.ssp.closet.dto.Product;

public interface ProductMapper {

	void insertProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(int productId);
	List<Product> getProductList();
	Product getProductDetail(int productId);
}
