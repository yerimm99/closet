package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssp.closet.dto.GroupBuy;
import com.ssp.closet.dto.Product;

@Mapper
public interface ProductMapper {

	void insertGroupBuyProduct(Product product, GroupBuy groupBuy);
	void updateProduct(int productId);
	void deleteProduct(int productId);
	List<Product> getProductList();
	Product getProductDetail(int productId);
}
