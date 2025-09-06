package com.ams.dao;

import com.ams.model.Flight;
import com.ams.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {
    
    public boolean addFlight(Flight flight) {
        String query = "INSERT INTO Flight (CarrierID, Origin, Destination, Airfare, SeatCapacityEconomyClass, SeatCapacityBusinessClass, SeatCapacityExecutiveClass) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, flight.getCarrierId());
            pstmt.setString(2, flight.getOrigin());
            pstmt.setString(3, flight.getDestination());
            pstmt.setInt(4, flight.getAirfare());
            pstmt.setInt(5, flight.getSeatCapacityEconomyClass());
            pstmt.setInt(6, flight.getSeatCapacityBusinessClass());
            pstmt.setInt(7, flight.getSeatCapacityExecutiveClass());
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    flight.setFlightId(rs.getInt(1));
                }
                return true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        String query = "SELECT f.*, c.CarrierName FROM Flight f JOIN Carrier c ON f.CarrierID = c.CarrierId ORDER BY f.FlightID";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("FlightID"));
                flight.setCarrierId(rs.getInt("CarrierID"));
                flight.setOrigin(rs.getString("Origin"));
                flight.setDestination(rs.getString("Destination"));
                flight.setAirfare(rs.getInt("Airfare"));
                flight.setSeatCapacityEconomyClass(rs.getInt("SeatCapacityEconomyClass"));
                flight.setSeatCapacityBusinessClass(rs.getInt("SeatCapacityBusinessClass"));
                flight.setSeatCapacityExecutiveClass(rs.getInt("SeatCapacityExecutiveClass"));
                flight.setCarrierName(rs.getString("CarrierName"));
                flight.setCreatedAt(rs.getTimestamp("CreatedAt"));
                flight.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                flights.add(flight);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
    
    public Flight getFlightById(int flightId) {
        String query = "SELECT f.*, c.CarrierName FROM Flight f JOIN Carrier c ON f.CarrierID = c.CarrierId WHERE f.FlightID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, flightId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("FlightID"));
                flight.setCarrierId(rs.getInt("CarrierID"));
                flight.setOrigin(rs.getString("Origin"));
                flight.setDestination(rs.getString("Destination"));
                flight.setAirfare(rs.getInt("Airfare"));
                flight.setSeatCapacityEconomyClass(rs.getInt("SeatCapacityEconomyClass"));
                flight.setSeatCapacityBusinessClass(rs.getInt("SeatCapacityBusinessClass"));
                flight.setSeatCapacityExecutiveClass(rs.getInt("SeatCapacityExecutiveClass"));
                flight.setCarrierName(rs.getString("CarrierName"));
                flight.setCreatedAt(rs.getTimestamp("CreatedAt"));
                flight.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                return flight;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateFlight(Flight flight) {
        String query = "UPDATE Flight SET CarrierID = ?, Origin = ?, Destination = ?, Airfare = ?, SeatCapacityEconomyClass = ?, SeatCapacityBusinessClass = ?, SeatCapacityExecutiveClass = ? WHERE FlightID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, flight.getCarrierId());
            pstmt.setString(2, flight.getOrigin());
            pstmt.setString(3, flight.getDestination());
            pstmt.setInt(4, flight.getAirfare());
            pstmt.setInt(5, flight.getSeatCapacityEconomyClass());
            pstmt.setInt(6, flight.getSeatCapacityBusinessClass());
            pstmt.setInt(7, flight.getSeatCapacityExecutiveClass());
            pstmt.setInt(8, flight.getFlightId());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteFlight(int flightId) {
        String query = "DELETE FROM Flight WHERE FlightID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, flightId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Flight> searchFlights(String origin, String destination, Date travelDate) {
        List<Flight> flights = new ArrayList<>();
        String query = "SELECT f.*, c.CarrierName FROM Flight f JOIN Carrier c ON f.CarrierID = c.CarrierId WHERE f.Origin = ? AND f.Destination = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, origin);
            pstmt.setString(2, destination);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("FlightID"));
                flight.setCarrierId(rs.getInt("CarrierID"));
                flight.setOrigin(rs.getString("Origin"));
                flight.setDestination(rs.getString("Destination"));
                flight.setAirfare(rs.getInt("Airfare"));
                flight.setSeatCapacityEconomyClass(rs.getInt("SeatCapacityEconomyClass"));
                flight.setSeatCapacityBusinessClass(rs.getInt("SeatCapacityBusinessClass"));
                flight.setSeatCapacityExecutiveClass(rs.getInt("SeatCapacityExecutiveClass"));
                flight.setCarrierName(rs.getString("CarrierName"));
                flights.add(flight);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
    
    public List<Flight> getFlightsByCarrier(int carrierId) {
        List<Flight> flights = new ArrayList<>();
        String query = "SELECT f.*, c.CarrierName FROM Flight f JOIN Carrier c ON f.CarrierID = c.CarrierId WHERE f.CarrierID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, carrierId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("FlightID"));
                flight.setCarrierId(rs.getInt("CarrierID"));
                flight.setOrigin(rs.getString("Origin"));
                flight.setDestination(rs.getString("Destination"));
                flight.setAirfare(rs.getInt("Airfare"));
                flight.setSeatCapacityEconomyClass(rs.getInt("SeatCapacityEconomyClass"));
                flight.setSeatCapacityBusinessClass(rs.getInt("SeatCapacityBusinessClass"));
                flight.setSeatCapacityExecutiveClass(rs.getInt("SeatCapacityExecutiveClass"));
                flight.setCarrierName(rs.getString("CarrierName"));
                flights.add(flight);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
    
    public List<String> getUniqueOrigins() {
        List<String> origins = new ArrayList<>();
        String query = "SELECT DISTINCT Origin FROM Flight ORDER BY Origin";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                origins.add(rs.getString("Origin"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return origins;
    }
    
    public List<String> getUniqueDestinations() {
        List<String> destinations = new ArrayList<>();
        String query = "SELECT DISTINCT Destination FROM Flight ORDER BY Destination";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                destinations.add(rs.getString("Destination"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }
}