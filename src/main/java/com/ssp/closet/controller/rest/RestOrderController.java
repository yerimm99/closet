package com.ssp.closet.controller.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.closet.dto.Delivery;
import com.ssp.closet.service.ClosetFacade;

@RestController
@RequestMapping("/rest")
public class RestOrderController {
	@Autowired
	private ClosetFacade closet;

	@Autowired
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}
	
	// @ResponseBody         /order/detail.do?orderId=34
	@GetMapping(value = "/order/detail/{orderId}", produces = "application/json")
	public Delivery getOrder(@PathVariable("orderId") int orderId, HttpServletResponse response)
			throws IOException {
		System.out.println("/rest/order/{orderId} request accepted: {orderId} = " + orderId);
		Delivery order = closet.getOrder(orderId);
		if (order == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return order;   // convert order to JSON text in response body
	}
	
	// @ResponseBody         
	@GetMapping(value = "/ordersBy/{username}", produces = "application/json")
	public List<Delivery> getOrdersByUsername(@PathVariable("userId") String userId, HttpServletResponse response)
			throws IOException {
		System.out.println("/rest/order/{userId} request accepted: {userId} = " + userId);
		List<Delivery> orderList = closet.getOrderList(userId);
		if (orderList == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return orderList;  // convert list of orders to JSON text in response body
	}
	
	
	//주문 삭제...? 있어야 하나ㅠㅠㅠ
//	// @ResponseBody
//	@DeleteMapping("/order/{orderId}")
//	@ResponseStatus(HttpStatus.OK)
//	public Order deleteOrder(@PathVariable("orderId") int orderId, HttpServletResponse response)
//			throws IOException {
//		System.out.println("/rest/order/{orderId} request with DELETE method accepted: {orderId} = " + orderId);
//		Order order = orderSvc.removeOrder(orderId);
//		if (order == null) {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		System.out.println("order " + order.getOrderId() + " deleted.");
//		return order;	 	// convert order to JSON text in response body
//	}
}