package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
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


  public List<Product> getProducts() {
      return products;
  }

  public void setProducts(List<Product> products) {
      this.products = products;
  }

  public String getUserId() { return userId; }
  public void setUserId(String userId) { this.userId = userId; }
 
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getAddress() {	return address; }
  public void setAddress(String address) { this.address = address; }
  
  public double getRating() { return rating; }
  public void setRating(double rating) { this.rating = rating; }
  
  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }

}
