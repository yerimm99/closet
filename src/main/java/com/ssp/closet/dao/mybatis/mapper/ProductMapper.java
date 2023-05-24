package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.GroupBuy;
import com.ssp.closet.dto.Product;

public interface ProductMapper {

	void insertAuctionProduct(Product product, Auction auction);
	void insertGroupBuyProduct(Product product, GroupBuy groupBuy);
	void updateProduct(Product product);
	void deleteProduct(int productId);
	List<Product> getProductList();
	Product getProductDetail(int productId);
}
