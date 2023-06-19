package com.ssp.closet.controller;

import java.util.List;

import com.ssp.closet.dto.Product;

public class LikeController {
	  private List<Product> products;

	    public LikeController(List<Product> products) {
	        this.products = products;
	    }

	    public void likeProduct(String productName) {
	        for (Product product : products) {
	            if (product.getName().equalsIgnoreCase(productName)) {
	                product.incrementLikes();
	                System.out.println(productName + " 상품에 좋아요를 눌렀습니다.");
	                return;
	                
	            }
	        }
	        System.out.println(productName + " 상품을 찾을 수 없습니다.");
	    }
	    
	    
}
