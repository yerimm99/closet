package com.ssp.closet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.ssp.closet.repository.ProductRepository;
import com.ssp.closet.dto.Product;
import java.util.List;

@Controller
public class RankController {
    
    private final ProductRepository productRepository;
    
    @Autowired
    public RankController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @GetMapping("/rank/rank.do")
    public String getRanking(Model model) {
        List<Product> topRankingProducts = productRepository.findTopRankingProducts();
        model.addAttribute("topRankingProducts", topRankingProducts);
        return "rank";
    }
}