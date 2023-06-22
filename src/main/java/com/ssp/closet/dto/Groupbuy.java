package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "GROUPBUY")
@DiscriminatorValue("Groupbuy")
@Getter
@Setter
public class Groupbuy extends Product implements Serializable {
	@Column(name = "PEOPLENUM")
	private Integer peopleNum; // 공동구매가 이뤄지기 위한 최소 인원
	
	@Column(name = "PEOPLESUM")
	private Integer peopleSum = 0; // 공동구매가 이뤄지기 위한 최소 인원
	
	public Groupbuy() {}
	
    @OneToMany(mappedBy = "groupbuy")
    private List<Meet> meets;

	public void initGroupbuy(Account account) {
		super.setDTYPE("Groupbuy");
		super.setStatus(1);
		super.setRegisterDate(new Date());
		super.setAccount(account);
	  }
}
