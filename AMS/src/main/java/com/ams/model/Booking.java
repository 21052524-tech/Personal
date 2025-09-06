package com.ams.model;

import java.util.Date;

public class Booking {
    private int bookingId;
    private int flightId;
    private int userId;
    private int noOfSeats;
    private String seatCategory;
    private Date dateOfTravel;
    private String bookingStatus;
    private double bookingAmount;
    private Date bookingDate;
    private Date updatedAt;
    
    // For joined queries
    private String userName;
    private String origin;
    private String destination;
    private String carrierName;
    private int airfare;
    
    // Default constructor
    public Booking() {}
    
    // Constructor without ID (for new bookings)
    public Booking(int flightId, int userId, int noOfSeats, String seatCategory,
                   Date dateOfTravel, String bookingStatus, double bookingAmount) {
        this.flightId = flightId;
        this.userId = userId;
        this.noOfSeats = noOfSeats;
        this.seatCategory = seatCategory;
        this.dateOfTravel = dateOfTravel;
        this.bookingStatus = bookingStatus;
        this.bookingAmount = bookingAmount;
    }
    
    // Full constructor
    public Booking(int bookingId, int flightId, int userId, int noOfSeats,
                   String seatCategory, Date dateOfTravel, String bookingStatus, 
                   double bookingAmount, Date bookingDate) {
        this.bookingId = bookingId;
        this.flightId = flightId;
        this.userId = userId;
        this.noOfSeats = noOfSeats;
        this.seatCategory = seatCategory;
        this.dateOfTravel = dateOfTravel;
        this.bookingStatus = bookingStatus;
        this.bookingAmount = bookingAmount;
        this.bookingDate = bookingDate;
    }
    
    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    
    public int getFlightId() {
        return flightId;
    }
    
    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getNoOfSeats() {
        return noOfSeats;
    }
    
    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }
    
    public String getSeatCategory() {
        return seatCategory;
    }
    
    public void setSeatCategory(String seatCategory) {
        this.seatCategory = seatCategory;
    }
    
    public Date getDateOfTravel() {
        return dateOfTravel;
    }
    
    public void setDateOfTravel(Date dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }
    
    public String getBookingStatus() {
        return bookingStatus;
    }
    
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    
    public double getBookingAmount() {
        return bookingAmount;
    }
    
    public void setBookingAmount(double bookingAmount) {
        this.bookingAmount = bookingAmount;
    }
    
    public Date getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public String getCarrierName() {
        return carrierName;
    }
    
    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }
    
    public int getAirfare() {
        return airfare;
    }
    
    public void setAirfare(int airfare) {
        this.airfare = airfare;
    }
    
    // Utility methods
    public boolean isActive() {
        return "Booked".equals(bookingStatus);
    }
    
    public boolean isCancelled() {
        return "Cancelled".equals(bookingStatus);
    }
    
    public boolean isCompleted() {
        return "Travel Completed".equals(bookingStatus);
    }
    
    public String getRoute() {
        return origin + " → " + destination;
    }
    
    public String getFormattedAmount() {
        return String.format("₹%.2f", bookingAmount);
    }
    
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", flightId=" + flightId +
                ", userId=" + userId +
                ", noOfSeats=" + noOfSeats +
                ", seatCategory='" + seatCategory + '\'' +
                ", dateOfTravel=" + dateOfTravel +
                ", bookingStatus='" + bookingStatus + '\'' +
                ", bookingAmount=" + bookingAmount +
                '}';
    }
}