package com.ams.model;

import java.util.Date;

public class FlightSchedule {
    private int flightId;
    private Date dateOfTravel;
    private int economySeatsBooked;
    private int businessSeatsBooked;
    private int executiveSeatsBooked;
    private Date createdAt;
    private Date updatedAt;
    
    // Default constructor
    public FlightSchedule() {}
    
    // Constructor
    public FlightSchedule(int flightId, Date dateOfTravel, int economySeatsBooked,
                         int businessSeatsBooked, int executiveSeatsBooked) {
        this.flightId = flightId;
        this.dateOfTravel = dateOfTravel;
        this.economySeatsBooked = economySeatsBooked;
        this.businessSeatsBooked = businessSeatsBooked;
        this.executiveSeatsBooked = executiveSeatsBooked;
    }
    
    // Getters and Setters
    public int getFlightId() {
        return flightId;
    }
    
    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
    
    public Date getDateOfTravel() {
        return dateOfTravel;
    }
    
    public void setDateOfTravel(Date dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }
    
    public int getEconomySeatsBooked() {
        return economySeatsBooked;
    }
    
    public void setEconomySeatsBooked(int economySeatsBooked) {
        this.economySeatsBooked = economySeatsBooked;
    }
    
    public int getBusinessSeatsBooked() {
        return businessSeatsBooked;
    }
    
    public void setBusinessSeatsBooked(int businessSeatsBooked) {
        this.businessSeatsBooked = businessSeatsBooked;
    }
    
    public int getExecutiveSeatsBooked() {
        return executiveSeatsBooked;
    }
    
    public void setExecutiveSeatsBooked(int executiveSeatsBooked) {
        this.executiveSeatsBooked = executiveSeatsBooked;
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
    public int getTotalSeatsBooked() {
        return economySeatsBooked + businessSeatsBooked + executiveSeatsBooked;
    }
    
    public int getSeatsBookedForClass(String seatClass) {
        switch (seatClass.toLowerCase()) {
            case "economy":
                return economySeatsBooked;
            case "business":
                return businessSeatsBooked;
            case "executive":
                return executiveSeatsBooked;
            default:
                return 0;
        }
    }
    
    public void addBookedSeats(String seatClass, int seats) {
        switch (seatClass.toLowerCase()) {
            case "economy":
                this.economySeatsBooked += seats;
                break;
            case "business":
                this.businessSeatsBooked += seats;
                break;
            case "executive":
                this.executiveSeatsBooked += seats;
                break;
        }
    }
    
    public void removeBookedSeats(String seatClass, int seats) {
        switch (seatClass.toLowerCase()) {
            case "economy":
                this.economySeatsBooked = Math.max(0, this.economySeatsBooked - seats);
                break;
            case "business":
                this.businessSeatsBooked = Math.max(0, this.businessSeatsBooked - seats);
                break;
            case "executive":
                this.executiveSeatsBooked = Math.max(0, this.executiveSeatsBooked - seats);
                break;
        }
    }
    
    @Override
    public String toString() {
        return "FlightSchedule{" +
                "flightId=" + flightId +
                ", dateOfTravel=" + dateOfTravel +
                ", economySeatsBooked=" + economySeatsBooked +
                ", businessSeatsBooked=" + businessSeatsBooked +
                ", executiveSeatsBooked=" + executiveSeatsBooked +
                '}';
    }
}