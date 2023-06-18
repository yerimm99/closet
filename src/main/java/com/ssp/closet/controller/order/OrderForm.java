package com.ssp.closet.controller.order;

import java.io.Serializable;

import com.ssp.closet.dto.Delivery;

@SuppressWarnings("serial")
public class OrderForm implements Serializable{
	private Delivery order;

	private boolean newOrder;
	
	public OrderForm(Delivery order) {
		this.order = order;
		this.newOrder = false;
	}
	
	public OrderForm() {
		this.order = new Delivery();
	}

	public Delivery getOrder() {
		return order;
	}
	public boolean isNewOrder() {
		return newOrder;
	}
}
