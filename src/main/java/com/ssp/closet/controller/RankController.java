/*
 * package com.ssp.closet.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestParam;
 * 
 * import com.ssp.closet.repository.ProductRepository; import
 * com.ssp.closet.repository.GroupbuyRepository; import
 * com.ssp.closet.repository.AuctionRepository;
 * 
 * import com.ssp.closet.dto.Product; import java.util.List;
 * 
 * @Controller public class RankController {
 * 
 * private final ProductRepository productRepository; private final
 * GroupbuyRepository groupbuyRepository; private final AuctionRepository
 * auctionRepository;
 * 
 * @Autowired public RankController(ProductRepository productRepository,
 * GroupbuyRepository groupbuyRepository, AuctionRepository auctionRepository) {
 * this.productRepository = productRepository; this.groupbuyRepository =
 * groupbuyRepository; this.auctionRepository = auctionRepository; }
 * 
 * @GetMapping("/closet/best.do") public String showBestRanking(Model model) {
 * List<Product> findTopRankingProducts =
 * productRepository.findTopRankingProducts(); model.addAttribute("productList",
 * findTopRankingProducts); return "/rank/list"; }
 * 
 * @GetMapping("/rank/main.do") public String showMainRankPage() { return
 * "/main/rank"; }
 * 
 * @GetMapping("/rank/auctionList.do") public String showAuctionList( Model
 * model,
 * 
 * @RequestParam(name = "DTYPE", defaultValue = "Auction") String dtype,
 * 
 * @RequestParam(name = "categoryId", defaultValue = "전체") String categoryId,
 * 
 * @RequestParam(name = "sortParam", defaultValue = "like") String sortParam ) {
 * // Add code here to retrieve auction list based on the parameters // Example:
 * List<Product> auctionList = auctionRepository.findAuctionList(dtype,
 * categoryId, sortParam); model.addAttribute("auctionList", auctionList);
 * return "/rank/auctionList"; }
 * 
 * @GetMapping("/rank/groupbuyList.do") public String showGroupBuyList( Model
 * model,
 * 
 * @RequestParam(name = "DTYPE", defaultValue = "Groupbuy") String dtype,
 * 
 * @RequestParam(name = "categoryId", defaultValue = "전체") String categoryId,
 * 
 * @RequestParam(name = "sortParam", defaultValue = "like") String sortParam ) {
 * // Add code here to retrieve group buy list based on the parameters //
 * Example: List<Product> groupBuyList =
 * groupbuyRepository.findGroupBuyList(dtype, categoryId, sortParam);
 * model.addAttribute("groupbuyRepository", groupBuyList); return
 * "/rank/groupbylist"; } }
 */