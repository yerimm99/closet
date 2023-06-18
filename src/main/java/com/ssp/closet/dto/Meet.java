package com.ssp.closet.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(MeetId.class)
@Table(name = "MEET")
public class Meet {
	@Id
	@Column(name = "USERID")
	private String userId;

	@Id
	@Column(name = "PRODUCTID")
	private int productId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "SIGNDATE")
	private Date signDate;
	
	@Column(name = "MEETRESULT")
	private int meetResult;
	
	
	@ManyToOne
	@JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID", insertable = false, updatable = false)
	private Groupbuy groupbuy;

	@ManyToOne
	@JoinColumn(name = "USERID", referencedColumnName = "USERID", insertable = false, updatable = false)
	private Account meeter;
	
	
	public void initMeet(Account account, Groupbuy g) {
		userId = account.getUserId();
		productId = g.getProductId();
		setMeeter(account);
		setGroupbuy(g);
		meetResult = 0;
		signDate = new Date();
	}
}
