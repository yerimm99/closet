package com.ssp.closet.controller.groupbuy;

import java.io.Serializable;

import com.ssp.closet.dto.Meet;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class MeetForm implements Serializable{
	private Meet meet;

	private boolean newMeet;
	
	public MeetForm(Meet meet) {
		this.meet = meet;
		this.newMeet = false;
	}
	
	public MeetForm() {
		this.meet = new Meet();
		this.newMeet = true;
	}
	
	public boolean isNewMeet() {
		return newMeet;
	}
}
