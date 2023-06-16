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
public class BidId implements Serializable {

    @Column(name = "USERID")
    private String userId;

    @Column(name = "PRODUCTID")
    private int productId;

    public BidId() {
        // 기본 생성자
    }

    public BidId(String userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    // equals() 메서드 구현
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BidId)) {
            return false;
        }
        BidId other = (BidId) obj;
        return Objects.equals(userId, other.userId) && productId == other.productId;
    }
    
 // hashCode() 메서드 구현
    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }

    // 필요에 따라 toString() 등의 메서드를 추가로 구현
}