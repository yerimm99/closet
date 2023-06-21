package com.ssp.closet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssp.closet.repository.ProductRepository;
import com.ssp.closet.repository.GroupbuyRepository;
import com.ssp.closet.repository.AuctionRepository;
import com.ssp.closet.repository.LikeMarkRepository;

import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Groupbuy;
import java.util.List;

@Controller
public class RankController {

    private final ProductRepository productRepository;
    private final GroupbuyRepository groupbuyRepository;
    private final AuctionRepository auctionRepository;
    private final LikeMarkRepository likeMarkRepository;

    @Autowired
    public RankController(ProductRepository productRepository, GroupbuyRepository groupbuyRepository, AuctionRepository auctionRepository, LikeMarkRepository likeMarkRepository) {
        this.productRepository = productRepository;
        this.groupbuyRepository = groupbuyRepository;
        this.auctionRepository = auctionRepository;
        this.likeMarkRepository = likeMarkRepository;
    }

    @GetMapping("/closet/best.do")
    public String showBestRanking(Model model) {
        List<Product> findTopRankingProducts = productRepository.findTop10RankingProducts();
        model.addAttribute("productList", findTopRankingProducts);
        return "/rank/list";
    }

    @GetMapping("/main/rank.do")
    public String showMainRankPage() {
        return "/main/rank";
    }
    
    @GetMapping("/rank/auctionList.do")
    public String showAuctionList(Model model) {
        List<Auction> auctionList = auctionRepository.findTop10ByOrderByMark();
        model.addAttribute("auctionList", auctionList);
        return "/rank/auctionList";
    }

    @GetMapping("/rank/groupbuyList.do")
    public String showGroupBuyList(Model model) {
        List<Groupbuy> groupBuyList = groupbuyRepository.findTop10ByOrderByMark();
        model.addAttribute("groupBuyList", groupBuyList);
        return "/rank/groupbylist";
    }
}
