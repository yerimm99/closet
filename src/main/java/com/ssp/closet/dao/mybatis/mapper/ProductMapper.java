package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import com.ssp.closet.dto.GroupBuy;
import com.ssp.closet.dto.Product;

public interface ProductMapper {

	void insertGroupBuyProduct(Product product, GroupBuy groupBuy);
	void updateProduct(int productId);
	void deleteProduct(int productId);
	List<Product> getProductList();
	Product getProductDetail(int productId);
}
