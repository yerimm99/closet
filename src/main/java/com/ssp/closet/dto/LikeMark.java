package com.ssp.closet.dto;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Data
@Table(name = "LIKEMARK")
public class LikeMark implements Serializable {

    /* Private Fields */
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
    @ManyToOne
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    private Account user;

    @Column(name = "MARK")
    private int mark;
}
