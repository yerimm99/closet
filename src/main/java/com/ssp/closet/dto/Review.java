package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SUPPREVIEW")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review implements Serializable{
    @Id
    @JoinColumn(name = "ORDERID", referencedColumnName = "ORDERID")
    @Column(name = "ORDERID")
    private int orderId;
    
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @Column(name = "USERID")
    private String userId;
    
    @Column(name = "WRITEDATE")
    @CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
    private Date writeDate;
    
    @Column(name = "CONTENT")
    private String content;
    
    @Column(name = "RATING")
    private float rating;
}
