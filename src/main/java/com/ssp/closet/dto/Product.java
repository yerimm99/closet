package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "PRODUCT")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product implements Serializable {

    /* Private Fields */
    @Id
    @SequenceGenerator(name = "PRODUCT_SEQ_GENERATOR", sequenceName = "PRODUCT_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ_GENERATOR")
    @Column(name = "PRODUCTID")
    private int productId;

    @Column(name = "CATEGORYID")
    private String categoryId;

    @Column(name = "PNAME")
    private String name;

    @Column(name = "PDESCRIPTION")
    private String description;

    @Column(name = "PTYPE")
    private int ptype;

    @Column(name = "STATUS")
    private int status;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "REGISTERDATE")
    private Date registerDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ENDDATE")
    private Date endDate;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "PSIZE")
    private String size;

    @Column(name = "PICTURE1")
    private String picture1;

    @Column(name = "PICTURE2")
    private String picture2;

    @Column(name = "PICTURE3")
    private String picture3;

    @Column(name = "PICTURE4")
    private String picture4;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "RANK")
    private Integer rank;
    
    @ManyToOne
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    private Account account;

}
