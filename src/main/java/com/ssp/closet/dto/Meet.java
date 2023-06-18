package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "MEET")
@DiscriminatorValue("Meet")
@Getter
@Setter
public class Meet implements Serializable{
	@Id
	@SequenceGenerator(name = "MEETID_SEQ_GENERATOR", sequenceName="MEETID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MEETID_SEQ_GENERATOR")
	@Column(name="MEETID")
	private int meetId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "SIGNDATE")
	private Date signDate;
	
	@Column(name = "MEETRESULT")
	private int meetResult;
	
	@Column(name = "PRODUCTID")
	private int productId;
	
	@Column(name = "USERID")
	private String userId;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID", insertable = false, updatable = false)
	private Groupbuy groupbuy;

	@ManyToOne
	@JoinColumn(name = "USERID", referencedColumnName = "USERID", insertable = false, updatable = false)
	private Account meeter;
	
	
	public Meet() {
	}

	public Meet(String userId, int productId) {
		this.userId = userId;
		this.productId = productId;
		this.signDate = new Date();
		this.meetResult = 0;
	}
}
