package com.ams.dao;

import com.ams.model.Carrier;
import com.ams.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrierDAO {
    
    public boolean addCarrier(Carrier carrier) {
        String query = "INSERT INTO Carrier (CarrierName, DiscountPercentageThirtyDaysAdvanceBooking, DiscountPercentageSixtyDaysAdvanceBooking, DiscountPercentageNinetyDaysAdvanceBooking, RefundPercentageForTicketCancellation2DaysBeforeTravelDate, RefundPercentageForTicketCancellation10DaysBeforeTravelDate, RefundPercentageForTicketCancellation20DaysBeforeTravelDate, BulkBookingDiscount, SilverUserDiscount, GoldUserDiscount, PlatinumUserDiscount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, carrier.getCarrierName());
            pstmt.setInt(2, carrier.getDiscountPercentageThirtyDaysAdvanceBooking());
            pstmt.setInt(3, carrier.getDiscountPercentageSixtyDaysAdvanceBooking());
            pstmt.setInt(4, carrier.getDiscountPercentageNinetyDaysAdvanceBooking());
            pstmt.setInt(5, carrier.getRefundPercentageForTicketCancellation2DaysBeforeTravelDate());
            pstmt.setInt(6, carrier.getRefundPercentageForTicketCancellation10DaysBeforeTravelDate());
            pstmt.setInt(7, carrier.getRefundPercentageForTicketCancellation20DaysBeforeTravelDate());
            pstmt.setInt(8, carrier.getBulkBookingDiscount());
            pstmt.setInt(9, carrier.getSilverUserDiscount());
            pstmt.setInt(10, carrier.getGoldUserDiscount());
            pstmt.setInt(11, carrier.getPlatinumUserDiscount());
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    carrier.setCarrierId(rs.getInt(1));
                }
                return true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Carrier> getAllCarriers() {
        List<Carrier> carriers = new ArrayList<>();
        String query = "SELECT * FROM Carrier ORDER BY CarrierId";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Carrier carrier = new Carrier();
                carrier.setCarrierId(rs.getInt("CarrierId"));
                carrier.setCarrierName(rs.getString("CarrierName"));
                carrier.setDiscountPercentageThirtyDaysAdvanceBooking(rs.getInt("DiscountPercentageThirtyDaysAdvanceBooking"));
                carrier.setDiscountPercentageSixtyDaysAdvanceBooking(rs.getInt("DiscountPercentageSixtyDaysAdvanceBooking"));
                carrier.setDiscountPercentageNinetyDaysAdvanceBooking(rs.getInt("DiscountPercentageNinetyDaysAdvanceBooking"));
                carrier.setRefundPercentageForTicketCancellation2DaysBeforeTravelDate(rs.getInt("RefundPercentageForTicketCancellation2DaysBeforeTravelDate"));
                carrier.setRefundPercentageForTicketCancellation10DaysBeforeTravelDate(rs.getInt("RefundPercentageForTicketCancellation10DaysBeforeTravelDate"));
                carrier.setRefundPercentageForTicketCancellation20DaysBeforeTravelDate(rs.getInt("RefundPercentageForTicketCancellation20DaysBeforeTravelDate"));
                carrier.setBulkBookingDiscount(rs.getInt("BulkBookingDiscount"));
                carrier.setSilverUserDiscount(rs.getInt("SilverUserDiscount"));
                carrier.setGoldUserDiscount(rs.getInt("GoldUserDiscount"));
                carrier.setPlatinumUserDiscount(rs.getInt("PlatinumUserDiscount"));
                carrier.setCreatedAt(rs.getTimestamp("CreatedAt"));
                carrier.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                carriers.add(carrier);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carriers;
    }
    
    public Carrier getCarrierById(int carrierId) {
        String query = "SELECT * FROM Carrier WHERE CarrierId = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, carrierId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Carrier carrier = new Carrier();
                carrier.setCarrierId(rs.getInt("CarrierId"));
                carrier.setCarrierName(rs.getString("CarrierName"));
                carrier.setDiscountPercentageThirtyDaysAdvanceBooking(rs.getInt("DiscountPercentageThirtyDaysAdvanceBooking"));
                carrier.setDiscountPercentageSixtyDaysAdvanceBooking(rs.getInt("DiscountPercentageSixtyDaysAdvanceBooking"));
                carrier.setDiscountPercentageNinetyDaysAdvanceBooking(rs.getInt("DiscountPercentageNinetyDaysAdvanceBooking"));
                carrier.setRefundPercentageForTicketCancellation2DaysBeforeTravelDate(rs.getInt("RefundPercentageForTicketCancellation2DaysBeforeTravelDate"));
                carrier.setRefundPercentageForTicketCancellation10DaysBeforeTravelDate(rs.getInt("RefundPercentageForTicketCancellation10DaysBeforeTravelDate"));
                carrier.setRefundPercentageForTicketCancellation20DaysBeforeTravelDate(rs.getInt("RefundPercentageForTicketCancellation20DaysBeforeTravelDate"));
                carrier.setBulkBookingDiscount(rs.getInt("BulkBookingDiscount"));
                carrier.setSilverUserDiscount(rs.getInt("SilverUserDiscount"));
                carrier.setGoldUserDiscount(rs.getInt("GoldUserDiscount"));
                carrier.setPlatinumUserDiscount(rs.getInt("PlatinumUserDiscount"));
                carrier.setCreatedAt(rs.getTimestamp("CreatedAt"));
                carrier.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                return carrier;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateCarrier(Carrier carrier) {
        String query = "UPDATE Carrier SET CarrierName = ?, DiscountPercentageThirtyDaysAdvanceBooking = ?, DiscountPercentageSixtyDaysAdvanceBooking = ?, DiscountPercentageNinetyDaysAdvanceBooking = ?, RefundPercentageForTicketCancellation2DaysBeforeTravelDate = ?, RefundPercentageForTicketCancellation10DaysBeforeTravelDate = ?, RefundPercentageForTicketCancellation20DaysBeforeTravelDate = ?, BulkBookingDiscount = ?, SilverUserDiscount = ?, GoldUserDiscount = ?, PlatinumUserDiscount = ? WHERE CarrierId = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, carrier.getCarrierName());
            pstmt.setInt(2, carrier.getDiscountPercentageThirtyDaysAdvanceBooking());
            pstmt.setInt(3, carrier.getDiscountPercentageSixtyDaysAdvanceBooking());
            pstmt.setInt(4, carrier.getDiscountPercentageNinetyDaysAdvanceBooking());
            pstmt.setInt(5, carrier.getRefundPercentageForTicketCancellation2DaysBeforeTravelDate());
            pstmt.setInt(6, carrier.getRefundPercentageForTicketCancellation10DaysBeforeTravelDate());
            pstmt.setInt(7, carrier.getRefundPercentageForTicketCancellation20DaysBeforeTravelDate());
            pstmt.setInt(8, carrier.getBulkBookingDiscount());
            pstmt.setInt(9, carrier.getSilverUserDiscount());
            pstmt.setInt(10, carrier.getGoldUserDiscount());
            pstmt.setInt(11, carrier.getPlatinumUserDiscount());
            pstmt.setInt(12, carrier.getCarrierId());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteCarrier(int carrierId) {
        String query = "DELETE FROM Carrier WHERE CarrierId = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, carrierId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteMultipleCarriers(List<Integer> carrierIds) {
        String query = "DELETE FROM Carrier WHERE CarrierId = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            conn.setAutoCommit(false);
            
            for (int carrierId : carrierIds) {
                pstmt.setInt(1, carrierId);
                pstmt.addBatch();
            }
            
            int[] results = pstmt.executeBatch();
            conn.commit();
            
            // Check if all deletions were successful
            for (int result : results) {
                if (result <= 0) {
                    return false;
                }
            }
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            try (Connection conn = DatabaseConnection.getConnection()) {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    public boolean hasFlights(int carrierId) {
        String query = "SELECT COUNT(*) FROM Flight WHERE CarrierID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, carrierId);
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