package com.ssp.closet.controller;

import java.io.Serializable;

import com.ssp.closet.dto.GroupBuy;
import com.ssp.closet.dto.Product;

@SuppressWarnings("serial")
public class GroupBuyForm implements Serializable {

	private final Product product = new Product();
	private final GroupBuy groupBuy = new GroupBuy();
	private boolean categoryIdRequired = false;
	private boolean nameRequired = false;
	private boolean descriptionRequired = false;
	private boolean periodRequired = false;
	private boolean picture1Required = false;
	private boolean picture2Required = false;

	public Product getProduct() {
		return product;
	}
	
	public GroupBuy getGroupBuy() {
		return groupBuy;
	}

	public void setCategoryIdRequired(boolean categoryIdRequired) {
		this.categoryIdRequired = categoryIdRequired;
	}

	public boolean isCategoryIdRequired() {
		return categoryIdRequired;
	}
	
	public void setNameRequired(boolean nameRequired) {
		this.nameRequired = nameRequired;
	}

	public boolean isNameRequired() {
		return nameRequired;
	}
	
	public void setDescriptionRequired(boolean descriptionRequired) {
		this.descriptionRequired = descriptionRequired;
	}

	public boolean isDescriptionRequired() {
		return descriptionRequired;
	}
	
	public void setPeriodRequired(boolean periodRequired) {
		this.periodRequired = periodRequired;
	}

	public boolean isPeriodRequired() {
		return periodRequired;
	}
	
	public void setPicture1Required(boolean picture1Required) {
		this.picture1Required = picture1Required;
	}

	public boolean isPicture1Required() {
		return picture1Required;
	}
	
	public void setPicture2Required(boolean picture2Required) {
		this.picture2Required = picture2Required;
	}

	public boolean isPicture2Required() {
		return picture2Required;
	}
}