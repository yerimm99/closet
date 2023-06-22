package com.ssp.closet.controller.order;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public String convertToFormattedDate(String date) {
		DateFormat inputFormat = new SimpleDateFormat("MM/yy");
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
		try {
			parsedDate = inputFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        String formattedDate = outputFormat.format(parsedDate);

	    return formattedDate;
	}
}
