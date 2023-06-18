package com.ssp.closet.dto;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BidId implements Serializable {

    @Column(name = "USERID")
    private String userId;

    @Column(name = "PRODUCTID")
    private int productId;

}