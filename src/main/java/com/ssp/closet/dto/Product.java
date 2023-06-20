package com.ssp.closet.dto;

import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
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

import javax.persistence.OrderBy;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@SuppressWarnings("serial")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "PRODUCT")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE") // 하위 테이블의 구분 컬럼 생성(default = DTYPE)
@Data
public class Product implements Serializable {

	/* Private Fields */
	@Id
	@SequenceGenerator(name = "PRODUCT_SEQ_GENERATOR", sequenceName="PRODUCT_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PRODUCT_SEQ_GENERATOR")
	@Column(name="PRODUCTID")
	private int productId;
	@Column(name="CATEGORYID")
	private String categoryId; // 상의? 하의? ....
	@Column(name="PNAME")
	private String name;
	@Column(name="PDESCRIPTION")
	private String description; // 상품 설명
	@Column(name="PTYPE")
	private int type; // 경매? 공동구매?
	@Column(name="STATUS")
	private int status; // 판매 상태
	@Column(name="REGISTERDATE")
	@CreatedDate
	@Temporal(TemporalType.DATE)
	private Date registerDate; // 등록 날짜
	@Column(name="ENDDATE")
	@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate; // 등록 날짜
	@Column(name="COLOR")
	private String color;
	@Column(name="PSIZE")
	private String size;
	@Column(name="PICTURE1")
	private String picture1;
	@Column(name="PICTURE2")
	private String picture2;
	@Column(name="PICTURE3")
	private String picture3;
	@Column(name="PICTURE4")
	private String picture4;
	@Column(name="PRICE")
	private Integer price;
	@Column(name = "DTYPE", insertable=false, updatable=false)
	private String DTYPE;
	
	/*
	 * @Column(name = "RANK")//랭킹
	 * 
	 * @OrderBy("rank DESC") private int rank;
	 * 
	 * @Column(name = "VIEWS") private int views; public int getViews() { return
	 * views;}
	 * 
	 * private int likes = 0; //관심상품 public void incrementLikes() {likes++;}
	 */

	@ManyToOne
	@JoinColumn(name = "USERID", referencedColumnName = "USERID")
	private Account account;

}