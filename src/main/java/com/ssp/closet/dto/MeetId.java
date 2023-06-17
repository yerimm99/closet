package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Embeddable
@Getter
@Setter
public class MeetId implements Serializable {
	 @Column(name = "USERID")
	    private String userId;

	    @Column(name = "PRODUCTID")
	    private int productId;

	    public MeetId() {
	    }

	    public MeetId(String userId, int productId) {
	        this.userId = userId;
	        this.productId = productId;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (!(obj instanceof MeetId)) {
	            return false;
	        }
	        MeetId other = (MeetId) obj;
	        return Objects.equals(userId, other.userId) && productId == other.productId;
	    }
	    
	    @Override
	    public int hashCode() {
	        return Objects.hash(userId, productId);
	    }
}
