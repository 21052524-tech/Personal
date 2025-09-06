package com.ams.dao;

import com.ams.model.Booking;
import com.ams.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    
    public boolean addBooking(Booking booking) {
        String query = "INSERT INTO FlightBooking (FlightID, UserID, NoOfSeats, SeatCategory, DateOfTravel, BookingStatus, BookingAmount) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, booking.getFlightId());
            pstmt.setInt(2, booking.getUserId());
            pstmt.setInt(3, booking.getNoOfSeats());
            pstmt.setString(4, booking.getSeatCategory());
            pstmt.setDate(5, new java.sql.Date(booking.getDateOfTravel().getTime()));
            pstmt.setString(6, booking.getBookingStatus());
            pstmt.setDouble(7, booking.getBookingAmount());
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    booking.setBookingId(rs.getInt(1));
                }
                return true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Booking> getBookingsByUserId(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM FlightBooking WHERE UserID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("Booking_ID"));
                booking.setFlightId(rs.getInt("FlightID"));
                booking.setUserId(rs.getInt("UserID"));
                booking.setNoOfSeats(rs.getInt("NoOfSeats"));
                booking.setSeatCategory(rs.getString("SeatCategory"));
                booking.setDateOfTravel(rs.getDate("DateOfTravel"));
                booking.setBookingStatus(rs.getString("BookingStatus"));
                booking.setBookingAmount(rs.getDouble("BookingAmount"));
                bookings.add(booking);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    
    public Booking getBookingById(int bookingId) {
        String query = "SELECT * FROM FlightBooking WHERE Booking_ID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, bookingId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("Booking_ID"));
                booking.setFlightId(rs.getInt("FlightID"));
                booking.setUserId(rs.getInt("UserID"));
                booking.setNoOfSeats(rs.getInt("NoOfSeats"));
                booking.setSeatCategory(rs.getString("SeatCategory"));
                booking.setDateOfTravel(rs.getDate("DateOfTravel"));
                booking.setBookingStatus(rs.getString("BookingStatus"));
                booking.setBookingAmount(rs.getDouble("BookingAmount"));
                return booking;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateBookingStatus(int bookingId, String status) {
        String query = "UPDATE FlightBooking SET BookingStatus = ? WHERE Booking_ID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, bookingId);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT fb.*, u.UserName FROM FlightBooking fb JOIN User u ON fb.UserID = u.UserID";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("Booking_ID"));
                booking.setFlightId(rs.getInt("FlightID"));
                booking.setUserId(rs.getInt("UserID"));
                booking.setNoOfSeats(rs.getInt("NoOfSeats"));
                booking.setSeatCategory(rs.getString("SeatCategory"));
                booking.setDateOfTravel(rs.getDate("DateOfTravel"));
                booking.setBookingStatus(rs.getString("BookingStatus"));
                booking.setBookingAmount(rs.getDouble("BookingAmount"));
                bookings.add(booking);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    
    public boolean hasActiveBookings(int flightId) {
        String query = "SELECT COUNT(*) FROM FlightBooking WHERE FlightID = ? AND BookingStatus = 'Booked'";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, flightId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}