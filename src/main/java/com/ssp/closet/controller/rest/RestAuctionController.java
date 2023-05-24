//package com.ssp.closet.controller.rest;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriComponents;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import com.ssp.closet.dto.Auction;
//import com.ssp.closet.service.AuctionService;
//
//@RestController
//@RequestMapping("/rest")
//public class RestAuctionController {
//	private AuctionService auctionSvc;
//	
//	@Value("${server.port}")
//	private String serverPort;
//	
//	@Autowired
//	public void setAuctionSvc(AuctionService auctionService) {
//		this.auctionSvc = auctionService;
//	}
//	@GetMapping(value = "/auction/{auctionId}", produces = "application/json")
//	// @ResponseBody
//	public Auction getProduct(@PathVariable("auctionId") int auctionId, HttpServletResponse response)
//			throws IOException {
//		System.out.println("GET /rest/auction/{auctionId} request accepted: {auctionId} = " + auctionId);
//		Auction auction = auctionSvc.getAuction(auctionId);
//		System.out.println(auction);
//		if (auction == null) {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		return auction;
//	}
//	
//	@GetMapping(value = "/auction/{userId}", produces = "application/json")
//	// @ResponseBody
//	public List<Auction> getAuctionResultList(@PathVariable("userId") String userId, HttpServletResponse response)
//			throws IOException {
//		System.out.println("GET /rest/auction/{userId} request accepted: {userId} = " + userId);
//		List<Auction> auctionList = auctionSvc.getAuctionResultList(userId);
//		
//		if (auctionList.size() == 0) {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		return auctionList;
//	}
//	
//	@PostMapping(value = "/auction", consumes = "application/json")
//	@ResponseStatus(HttpStatus.CREATED)
//	public void insertAuctionPrice(@RequestBody Auction auction, HttpServletResponse response) throws IOException {
//		System.out.println("POST /rest/auction request accepted with a auction: " + auction);		
//
//		
//		if (auctionSvc.getAuction(auction.getAuctionId()) != null) {
//			response.sendError(HttpServletResponse.SC_CONFLICT);
//			return;
//		}
//		auctionSvc.insertAuctionPrice(auction);		 
//        String ipAddr = InetAddress.getLocalHost().getHostAddress();
//		UriComponents uriComp = UriComponentsBuilder
//									.newInstance()
//									.scheme("http")
//									.host(ipAddr)
//									.port(serverPort)
//									.path("/jpetstore/auction/{auctionId}")
//									.build();
//		UriComponents encodedUriComp = uriComp.expand(auction.getAuctionId()).encode();
//		response.setHeader("Location", encodedUriComp.toUriString());
//		
//		System.out.println("auction " + auction.getAuctionId() + " created.");
//	}
//	
//	@PutMapping(value = "/auction/{auctionId}", consumes = "application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public void updateAuctionPrice(@PathVariable("auctionId") int auctionId, 
//			@RequestBody Auction auction, HttpServletResponse response) throws IOException {
//		System.out.println("PUT /rest/auction/{auctionId} request accepted: {auctionId} = " + auctionId + ", auction = " + auction);
//		if (auctionSvc.getAuction(auctionId) == null) {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return;
//		}
//		auctionSvc.updateAuctionPrice(auctionId, auction);
//		System.out.println("auction " + auctionId + " updated.");
//	}
//	
//	@DeleteMapping(value = "/auction/{auctionId}")
//	@ResponseStatus(HttpStatus.OK)
//	// @ResponseBody
//	public void deleteAuctionByUser(@PathVariable("auctionId") int auctionId, HttpServletResponse response)
//			throws IOException {
//		System.out.println("DELETE /rest/auction/{auctionId} request accepted: {auctionId} = " + auctionId);
//
//
//		if (auctionSvc.getAuction(auctionId) == null) {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return;
//		}
//		auctionSvc.deleteAuctionByUser(auctionId);
//		System.out.println("auction " + auctionId + " deleted.");
//	}
//}
