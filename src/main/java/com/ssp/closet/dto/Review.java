package com.ssp.closet.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @JoinColumn(name = "ORDERID", referencedColumnName = "ORDERID")
    @Column(name = "ORDERID")
    private int orderId;
    
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")
    @Column(name = "PRODUCTID")
    private String productId;
    
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @Column(name = "USERID")
    private String userId;
    
    @Column(name = "PNAME")
    private String name;
    
    @Column(name = "WRITEDATE")
    private Date writeDate;
    
    @Column(name = "CONTENT")
    private String content;
    
    @Column(name = "RATING")
    private double rating;
}
