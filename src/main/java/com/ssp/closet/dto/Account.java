package com.ssp.closet.dto;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Account implements Serializable {

  /* Private Fields */

  private String userId;
  private String password;
  private String email;
  private String name;
  private String address;
  private String phone;
  private double rating;

  /* JavaBeans Properties */



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
