package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "ACCOUNT")
public class Account implements Serializable {

  /* Private Fields */
  @Id
  @Column(name="USERID")
  private String userId;
  @Column(name="PASSWORD")
  private String password;
  @Column(name="EMAIL")
  private String email;
  @Column(name="USERNAME")
  private String name;
  @Column(name="ADDRESS")
  private String address;
  @Column(name="PHONE")
  private String phone;
  @Column(name="RATING")
  private double rating;

  @OneToMany(mappedBy = "bidder")
  private List<Bid> bids;

  @OneToMany(mappedBy = "account")
  private List<Product> products;

}
