//package com.ssp.closet.service;
//
//import com.ssp.closet.repository.ProductRepository;
//import com.ssp.closet.dto.Product;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Comparator;
//import java.util.List;
//
//@Service
//public class RankService {
//	  @Autowired
//	    private ProductRepository productRepository;
//
//	    public void calculateRankingByViews() {
//	        List<Product> products = productRepository.findAll();
//
//	        // Sort the products based on views in descending order
//	        products.sort(Comparator.comparing(Product::getViews).reversed());
//
//	        // Assign ranks based on the sorted order
//	        for (int i = 0; i < products.size(); i++) {
//	            Product product = products.get(i);
//	            product.setRank(i + 1);
//	            productRepository.save(product);
//	        }
//	    }
//}
