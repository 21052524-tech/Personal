package com.ams.model;

import java.util.Date;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String role;
    private String customerCategory;
    private long phone;
    private String emailId;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private int zipCode;
    private Date dob;
    private Date createdAt;
    private Date updatedAt;
    
    // Default constructor
    public User() {}
    
    // Constructor with essential fields
    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
    
    // Full constructor
    public User(int userId, String userName, String password, String role, 
                String customerCategory, long phone, String emailId, 
                String address1, String address2, String city, String state, 
                String country, int zipCode, Date dob) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.customerCategory = customerCategory;
        this.phone = phone;
        this.emailId = emailId;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.dob = dob;
    }
    
    // Getters and Setters
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getCustomerCategory() {
        return customerCategory;
    }
    
    public void setCustomerCategory(String customerCategory) {
        this.customerCategory = customerCategory;
    }
    
    public long getPhone() {
        return phone;
    }
    
    public void setPhone(long phone) {
        this.phone = phone;
    }
    
    public String getEmailId() {
        return emailId;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    public String getAddress1() {
        return address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    
    public String getAddress2() {
        return address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public int getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    
    public Date getDob() {
        return dob;
    }
    
    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    // Utility methods
    public boolean isAdmin() {
        return "Admin".equals(this.role);
    }
    
    public boolean isCustomer() {
        return "Customer".equals(this.role);
    }
    
    public boolean isPremiumCustomer() {
        return "Gold".equals(this.customerCategory) || "Platinum".equals(this.customerCategory);
    }
    
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                ", customerCategory='" + customerCategory + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}