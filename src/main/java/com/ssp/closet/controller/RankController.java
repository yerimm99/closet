package com.ssp.closet.controller;

import com.ssp.closet.dto.Product;
import com.ssp.closet.service.ClosetFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RankController {
    
    private final ClosetFacade closetFacade;
    
    @Autowired
    public RankController(ClosetFacade closetFacade) {
        this.closetFacade = closetFacade;
    }
    
    @GetMapping("/rank")
    public String getRanking(Model model) {
        List<Product> topRankingProducts = closetFacade.getTopRankingProducts();
        model.addAttribute("topRankingProducts", topRankingProducts);
        return "rank";
    }
}