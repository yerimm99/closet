package com.ssp.closet.controller;

import java.io.Serializable;
import com.ssp.closet.dto.*;

@SuppressWarnings("serial")
public class UserSession implements Serializable {

	private Account account;

	public UserSession(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}


}