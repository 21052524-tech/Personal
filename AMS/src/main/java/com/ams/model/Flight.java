package com.ams.model;

import java.util.Date;

public class Flight {
    private int flightId;
    private int carrierId;
    private String origin;
    private String destination;
    private int airfare;
    private int seatCapacityEconomyClass;
    private int seatCapacityBusinessClass;
    private int seatCapacityExecutiveClass;
    private Date createdAt;
    private Date updatedAt;
    
    // For joined queries
    private String carrierName;
    
    // Default constructor
    public Flight() {}
    
    // Constructor without ID (for new flights)
    public Flight(int carrierId, String origin, String destination, int airfare,
                  int seatCapacityEconomyClass, int seatCapacityBusinessClass, 
                  int seatCapacityExecutiveClass) {
        this.carrierId = carrierId;
        this.origin = origin;
        this.destination = destination;
        this.airfare = airfare;
        this.seatCapacityEconomyClass = seatCapacityEconomyClass;
        this.seatCapacityBusinessClass = seatCapacityBusinessClass;
        this.seatCapacityExecutiveClass = seatCapacityExecutiveClass;
    }
    
    // Full constructor
    public Flight(int flightId, int carrierId, String origin, String destination, 
                  int airfare, int seatCapacityEconomyClass, int seatCapacityBusinessClass, 
                  int seatCapacityExecutiveClass) {
        this.flightId = flightId;
        this.carrierId = carrierId;
        this.origin = origin;
        this.destination = destination;
        this.airfare = airfare;
        this.seatCapacityEconomyClass = seatCapacityEconomyClass;
        this.seatCapacityBusinessClass = seatCapacityBusinessClass;
        this.seatCapacityExecutiveClass = seatCapacityExecutiveClass;
    }
    
    // Getters and Setters
    public int getFlightId() {
        return flightId;
    }
    
    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
    
    public int getCarrierId() {
        return carrierId;
    }
    
    public void setCarrierId(int carrierId) {
        this.carrierId = carrierId;
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
    
    public int getAirfare() {
        return airfare;
    }
    
    public void setAirfare(int airfare) {
        this.airfare = airfare;
    }
    
    public int getSeatCapacityEconomyClass() {
        return seatCapacityEconomyClass;
    }
    
    public void setSeatCapacityEconomyClass(int seatCapacityEconomyClass) {
        this.seatCapacityEconomyClass = seatCapacityEconomyClass;
    }
    
    public int getSeatCapacityBusinessClass() {
        return seatCapacityBusinessClass;
    }
    
    public void setSeatCapacityBusinessClass(int seatCapacityBusinessClass) {
        this.seatCapacityBusinessClass = seatCapacityBusinessClass;
    }
    
    public int getSeatCapacityExecutiveClass() {
        return seatCapacityExecutiveClass;
    }
    
    public void setSeatCapacityExecutiveClass(int seatCapacityExecutiveClass) {
        this.seatCapacityExecutiveClass = seatCapacityExecutiveClass;
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
    
    public String getCarrierName() {
        return carrierName;
    }
    
    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }
    
    // Utility methods
    public int getTotalCapacity() {
        return seatCapacityEconomyClass + seatCapacityBusinessClass + seatCapacityExecutiveClass;
    }
    
    public int getCapacityForClass(String seatClass) {
        switch (seatClass.toLowerCase()) {
            case "economy":
                return seatCapacityEconomyClass;
            case "business":
                return seatCapacityBusinessClass;
            case "executive":
                return seatCapacityExecutiveClass;
            default:
                return 0;
        }
    }
    
    public double getClassMultiplier(String seatClass) {
        switch (seatClass.toLowerCase()) {
            case "economy":
                return 1.0;
            case "business":
                return 2.0;
            case "executive":
                return 5.0;
            default:
                return 1.0;
        }
    }
    
    public String getRoute() {
        return origin + " → " + destination;
    }
    
    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", carrierId=" + carrierId +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", airfare=" + airfare +
                ", totalCapacity=" + getTotalCapacity() +
                '}';
    }
}