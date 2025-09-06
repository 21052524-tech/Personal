package com.ams.service;

import com.ams.dao.BookingDAO;
import com.ams.dao.FlightDAO;
import com.ams.dao.CarrierDAO;
import com.ams.model.Booking;
import com.ams.model.Flight;
import com.ams.model.Carrier;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookingService {
    
    private BookingDAO bookingDAO = new BookingDAO();
    private FlightDAO flightDAO = new FlightDAO();
    private CarrierDAO carrierDAO = new CarrierDAO();
    private FlightService flightService = new FlightService();
    
    public boolean createBooking(Booking booking, String customerCategory) {
        // Validate flight exists
        Flight flight = flightDAO.getFlightById(booking.getFlightId());
        if (flight == null) {
            return false;
        }
        
        // Check seat availability
        if (!flightService.isSeatsAvailable(booking.getFlightId(), booking.getSeatCategory(), 
                                           booking.getNoOfSeats(), booking.getDateOfTravel())) {
            return false;
        }
        
        // Calculate fare
        double fare = flightService.calculateFare(flight, booking.getSeatCategory(), 
                                                 booking.getNoOfSeats(), customerCategory, 
                                                 booking.getDateOfTravel());
        booking.setBookingAmount(fare);
        
        return bookingDAO.addBooking(booking);
    }
    
    public List<Booking> getBookingsByUserId(int userId) {
        return bookingDAO.getBookingsByUserId(userId);
    }
    
    public Booking getBookingById(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }
    
    public boolean cancelBooking(int bookingId) {
        Booking booking = bookingDAO.getBookingById(bookingId);
        if (booking == null || !"Booked".equals(booking.getBookingStatus())) {
            return false;
        }
        
        return bookingDAO.updateBookingStatus(bookingId, "Cancelled");
    }
    
    public double calculateRefund(int bookingId) {
        Booking booking = bookingDAO.getBookingById(bookingId);
        if (booking == null) {
            return 0.0;
        }
        
        Flight flight = flightDAO.getFlightById(booking.getFlightId());
        if (flight == null) {
            return 0.0;
        }
        
        Carrier carrier = carrierDAO.getCarrierById(flight.getCarrierId());
        if (carrier == null) {
            return 0.0;
        }
        
        // Calculate days before travel
        long diffInMillies = booking.getDateOfTravel().getTime() - new Date().getTime();
        int daysBeforeTravel = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        
        // Get refund percentage based on cancellation timing
        int refundPercentage = carrier.getRefundPercentageForDays(daysBeforeTravel);
        
        return booking.getBookingAmount() * (refundPercentage / 100.0);
    }
    
    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }
    
    public List<Booking> getUpcomingBookings(int userId) {
        List<Booking> allBookings = bookingDAO.getBookingsByUserId(userId);
        Date today = new Date();
        
        return allBookings.stream()
                .filter(booking -> "Booked".equals(booking.getBookingStatus()) && 
                                 booking.getDateOfTravel().after(today))
                .collect(java.util.stream.Collectors.toList());
    }
    
    public List<Booking> getCompletedBookings(int userId) {
        List<Booking> allBookings = bookingDAO.getBookingsByUserId(userId);
        
        return allBookings.stream()
                .filter(booking -> "Travel Completed".equals(booking.getBookingStatus()))
                .collect(java.util.stream.Collectors.toList());
    }
    
    public List<Booking> getCancelledBookings(int userId) {
        List<Booking> allBookings = bookingDAO.getBookingsByUserId(userId);
        
        return allBookings.stream()
                .filter(booking -> "Cancelled".equals(booking.getBookingStatus()))
                .collect(java.util.stream.Collectors.toList());
    }
    
    public boolean updateBookingStatus(int bookingId, String status) {
        return bookingDAO.updateBookingStatus(bookingId, status);
    }
}