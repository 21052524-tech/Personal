package com.ams.service;

import com.ams.dao.FlightDAO;
import com.ams.dao.CarrierDAO;
import com.ams.dao.BookingDAO;
import com.ams.model.Flight;
import com.ams.model.Carrier;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightService {
    
    private FlightDAO flightDAO = new FlightDAO();
    private CarrierDAO carrierDAO = new CarrierDAO();
    private BookingDAO bookingDAO = new BookingDAO();
    
    public boolean addFlight(Flight flight) {
        // Validate carrier exists
        Carrier carrier = carrierDAO.getCarrierById(flight.getCarrierId());
        if (carrier == null) {
            return false;
        }
        
        return flightDAO.addFlight(flight);
    }
    
    public List<Flight> getAllFlights() {
        return flightDAO.getAllFlights();
    }
    
    public Flight getFlightById(int flightId) {
        return flightDAO.getFlightById(flightId);
    }
    
    public boolean updateFlight(Flight flight) {
        return flightDAO.updateFlight(flight);
    }
    
    public boolean deleteFlight(int flightId) {
        // Check if there are active bookings
        if (bookingDAO.hasActiveBookings(flightId)) {
            return false;
        }
        
        return flightDAO.deleteFlight(flightId);
    }
    
    public List<Flight> searchFlights(String origin, String destination, Date travelDate) {
    	java.sql.Date sqlDate = new java.sql.Date(travelDate.getTime());
        return flightDAO.searchFlights(origin, destination, sqlDate);
    }
    
    public double calculateFare(Flight flight, String seatCategory, int noOfSeats, 
                               String customerCategory, Date travelDate) {
        
        // Base fare calculation
        double baseFare = flight.getAirfare();
        double classMultiplier = flight.getClassMultiplier(seatCategory);
        double totalFare = baseFare * classMultiplier * noOfSeats;
        
        // Get carrier for discount calculations
        Carrier carrier = carrierDAO.getCarrierById(flight.getCarrierId());
        if (carrier == null) {
            return totalFare;
        }
        
        // Calculate days before travel
        long diffInMillies = travelDate.getTime() - new Date().getTime();
        int daysBeforeTravel = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        
        // Apply advance booking discount
        int advanceDiscount = carrier.getDiscountForDaysAdvance(daysBeforeTravel);
        totalFare -= totalFare * (advanceDiscount / 100.0);
        
        // Apply customer category discount
        if (customerCategory != null) {
            int customerDiscount = carrier.getDiscountForCustomerCategory(customerCategory);
            totalFare -= totalFare * (customerDiscount / 100.0);
        }
        
        // Apply bulk booking discount (10+ seats)
        if (noOfSeats >= 10) {
            int bulkDiscount = carrier.getBulkBookingDiscount();
            totalFare -= totalFare * (bulkDiscount / 100.0);
        }
        
        return Math.round(totalFare * 100.0) / 100.0; // Round to 2 decimal places
    }
    
    public boolean isSeatsAvailable(int flightId, String seatCategory, int requestedSeats, Date travelDate) {
        Flight flight = flightDAO.getFlightById(flightId);
        if (flight == null) {
            return false;
        }
        
        int totalCapacity = flight.getCapacityForClass(seatCategory);
        
        // Check current bookings for this flight and date
        // This would require a more complex query to check FlightSchedule table
        // For now, we'll assume seats are available if within capacity
        
        return requestedSeats <= totalCapacity;
    }
    
    public List<String> getUniqueOrigins() {
        return flightDAO.getUniqueOrigins();
    }
    
    public List<String> getUniqueDestinations() {
        return flightDAO.getUniqueDestinations();
    }
    
    public List<Flight> getFlightsByCarrier(int carrierId) {
        return flightDAO.getFlightsByCarrier(carrierId);
    }
}