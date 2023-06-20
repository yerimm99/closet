package com.ssp.closet.controller.groupbuy;

import java.io.Serializable;

import com.ssp.closet.dto.Groupbuy;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class GroupbuyForm implements Serializable {

	private Groupbuy groupbuy;

	private boolean newGroupbuy;
	
	public GroupbuyForm(Groupbuy groupbuy) {
		this.groupbuy = groupbuy;
		this.newGroupbuy = false;
	}
	
	public GroupbuyForm() {
		this.groupbuy = new Groupbuy();
	}

	public Groupbuy getGroupbuy() {
		return groupbuy;
	}
	public boolean isNewGroupbuy() {
		return newGroupbuy;
	}
}
