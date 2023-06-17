package com.ssp.closet.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "MEET")
@DiscriminatorValue("Meet")
@Getter
@Setter
public class Meet{
	@EmbeddedId
	private MeetId meetId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "SIGNDATE")
	private Date signDate;
	@Column(name = "MEETRESULT")
	private int meetResult;
	
	@ManyToOne
	@MapsId("userId")
    @JoinColumn(name = "USERID")
    private Account meeter;
	
	@ManyToOne
	@MapsId("productId")
    @JoinColumn(name = "PRODUCTID")
    private Groupbuy groupbuy;

	
	public String getMeeterUserId() {
        return meeter.getUserId();
    }
	
	public void initMeet(Account account, Groupbuy g) {
		meeter = account;
		groupbuy = g;
		meetResult = 0;
		signDate = new Date();
	}
}
