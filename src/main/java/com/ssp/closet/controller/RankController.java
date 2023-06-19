package com.ssp.closet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.closet.service.RankService;
import com.ssp.closet.dto.Product;
import com.ssp.closet.repository.ProductRepository;

@RestController
@RequestMapping("/rank/rankForm.do")
public class RankController {
	 @Autowired
	 private RankService rankService;
	 private ProductRepository productRepository;

	    @GetMapping("/rank")
	    public List<Product> getRankedProducts() {
	        // 랭킹을 계산합니다.
	    	rankService.calculateRankingByViews();

	        // 랭킹을 기준으로 상품을 조회하고 반환합니다.
	        List<Product> products = productRepository.findByOrderByRank();
	        return products;
	    }
}