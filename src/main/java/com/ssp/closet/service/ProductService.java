package com.ssp.closet.service;

import java.util.List;

import com.ssp.closet.dto.Product;

public interface ProductService {
	List<Product> getProductList();
	Product getProductDetail(int productId);
}
