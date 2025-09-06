package com.ams.dao;

import com.ams.model.User;
import com.ams.util.DatabaseConnection;
import com.ams.service.AuthService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    
    private AuthService authService = new AuthService();
    
    public boolean registerUser(User user) {
        String query = "INSERT INTO User (UserName, Password, Role, CustomerCategory, Phone, EmailId, Address1, Address2, City, State, Country, ZipCode, DOB) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            // Encrypt password before storing
            String encryptedPassword = authService.encryptPassword(user.getPassword());
            
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, encryptedPassword);
            pstmt.setString(3, user.getRole());
            pstmt.setString(4, user.getCustomerCategory());
            pstmt.setLong(5, user.getPhone());
            pstmt.setString(6, user.getEmailId());
            pstmt.setString(7, user.getAddress1());
            pstmt.setString(8, user.getAddress2());
            pstmt.setString(9, user.getCity());
            pstmt.setString(10, user.getState());
            pstmt.setString(11, user.getCountry());
            pstmt.setInt(12, user.getZipCode());
            pstmt.setDate(13, new java.sql.Date(user.getDob().getTime()));
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setUserId(rs.getInt(1));
                }
                return true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public User validateUser(int userId, String password) {
        String query = "SELECT * FROM User WHERE UserID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String storedPassword = rs.getString("Password");
                String decryptedPassword = authService.decryptPassword(storedPassword);
                
                if (password.equals(decryptedPassword)) {
                    User user = new User();
                    user.setUserId(rs.getInt("UserID"));
                    user.setUserName(rs.getString("UserName"));
                    user.setPassword(decryptedPassword);
                    user.setRole(rs.getString("Role"));
                    user.setCustomerCategory(rs.getString("CustomerCategory"));
                    user.setPhone(rs.getLong("Phone"));
                    user.setEmailId(rs.getString("EmailId"));
                    user.setAddress1(rs.getString("Address1"));
                    user.setAddress2(rs.getString("Address2"));
                    user.setCity(rs.getString("City"));
                    user.setState(rs.getString("State"));
                    user.setCountry(rs.getString("Country"));
                    user.setZipCode(rs.getInt("ZipCode"));
                    user.setDob(rs.getDate("DOB"));
                    user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                    user.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                    return user;
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public User getUserById(int userId) {
        String query = "SELECT * FROM User WHERE UserID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setPassword(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
                user.setCustomerCategory(rs.getString("CustomerCategory"));
                user.setPhone(rs.getLong("Phone"));
                user.setEmailId(rs.getString("EmailId"));
                user.setAddress1(rs.getString("Address1"));
                user.setAddress2(rs.getString("Address2"));
                user.setCity(rs.getString("City"));
                user.setState(rs.getString("State"));
                user.setCountry(rs.getString("Country"));
                user.setZipCode(rs.getInt("ZipCode"));
                user.setDob(rs.getDate("DOB"));
                user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                user.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                return user;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateUser(User user) {
        String query = "UPDATE User SET UserName = ?, Phone = ?, EmailId = ?, Address1 = ?, Address2 = ?, City = ?, State = ?, Country = ?, ZipCode = ?, DOB = ? WHERE UserID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, user.getUserName());
            pstmt.setLong(2, user.getPhone());
            pstmt.setString(3, user.getEmailId());
            pstmt.setString(4, user.getAddress1());
            pstmt.setString(5, user.getAddress2());
            pstmt.setString(6, user.getCity());
            pstmt.setString(7, user.getState());
            pstmt.setString(8, user.getCountry());
            pstmt.setInt(9, user.getZipCode());
            pstmt.setDate(10, new java.sql.Date(user.getDob().getTime()));
            pstmt.setInt(11, user.getUserId());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User ORDER BY CreatedAt DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setRole(rs.getString("Role"));
                user.setCustomerCategory(rs.getString("CustomerCategory"));
                user.setPhone(rs.getLong("Phone"));
                user.setEmailId(rs.getString("EmailId"));
                user.setAddress1(rs.getString("Address1"));
                user.setAddress2(rs.getString("Address2"));
                user.setCity(rs.getString("City"));
                user.setState(rs.getString("State"));
                user.setCountry(rs.getString("Country"));
                user.setZipCode(rs.getInt("ZipCode"));
                user.setDob(rs.getDate("DOB"));
                user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                user.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                users.add(user);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM User WHERE UserID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateCustomerCategory(int userId, String category) {
        String query = "UPDATE User SET CustomerCategory = ? WHERE UserID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, category);
            pstmt.setInt(2, userId);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<User> getCustomersByCategory(String category) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User WHERE Role = 'Customer' AND CustomerCategory = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setRole(rs.getString("Role"));
                user.setCustomerCategory(rs.getString("CustomerCategory"));
                user.setPhone(rs.getLong("Phone"));
                user.setEmailId(rs.getString("EmailId"));
                user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                users.add(user);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}