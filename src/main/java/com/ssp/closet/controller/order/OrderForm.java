package com.ssp.closet.controller.order;

import java.io.Serializable;

import com.ssp.closet.dto.Order;

@SuppressWarnings("serial")
public class OrderForm implements Serializable{
	private Order order;

	private boolean newOrder;
	
	public OrderForm(Order order) {
		this.order = order;
		this.newOrder = false;
	}
	
	public OrderForm() {
		this.order = new Order();
	}

	public Order getOrder() {
		return order;
	}
	public boolean isNewOrder() {
		return newOrder;
	}
}
