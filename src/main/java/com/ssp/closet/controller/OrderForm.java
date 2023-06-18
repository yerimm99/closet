package com.ssp.closet.controller;

import java.io.Serializable;

import com.ssp.closet.dto.Delivery;

@SuppressWarnings("serial")
public class OrderForm implements Serializable {

	private final Delivery order = new Delivery();
	private boolean shippingAddressRequired = false;
	private boolean shippingAddressProvided = false;

	public Delivery getOrder() {
		return order;
	}

	public void setShippingAddressRequired(boolean shippingAddressRequired) {
		this.shippingAddressRequired = shippingAddressRequired;
	}

	public boolean isShippingAddressRequired() {
		return shippingAddressRequired;
	}

	public void setShippingAddressProvided(boolean shippingAddressProvided) {
		this.shippingAddressProvided = shippingAddressProvided;
	}

	public boolean didShippingAddressProvided() {
		return shippingAddressProvided;
	}
}
