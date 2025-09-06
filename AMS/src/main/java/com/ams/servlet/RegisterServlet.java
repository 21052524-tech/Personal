package com.ams.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ams.util.DatabaseConnection;
import com.ams.util.PasswordUtil;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get form parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dobStr = request.getParameter("dob");
        String emailId = request.getParameter("emailId");
        String address = request.getParameter("address1");
        String contactNumber = request.getParameter("phone");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String zipCode = request.getParameter("zipCode");
        
        // Validate required fields
        if (firstName == null || firstName.trim().isEmpty() ||
            lastName == null || lastName.trim().isEmpty() ||
            dobStr == null || dobStr.trim().isEmpty() ||
            emailId == null || emailId.trim().isEmpty() ||
            address == null || address.trim().isEmpty() ||
            contactNumber == null || contactNumber.trim().isEmpty() ||
            city == null || city.trim().isEmpty() ||
            state == null || state.trim().isEmpty() ||
            country == null || country.trim().isEmpty() ||
            zipCode == null || zipCode.trim().isEmpty()) {
            
            request.setAttribute("message", "All fields are mandatory");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Validate date of birth
        try {
            LocalDate dob = LocalDate.parse(dobStr);
            LocalDate minDate = LocalDate.of(1924, 1, 1);
            if (dob.isBefore(minDate)) {
                request.setAttribute("message", "Choose a date greater than 1/1/1924");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.setAttribute("message", "Invalid date format");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Validate contact number
        if (!contactNumber.matches("\\d{10}")) {
            request.setAttribute("message", "Enter a valid contact number");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Validate email format
        if (!emailId.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            request.setAttribute("message", "Enter a valid mail id");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Register user
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            
            // Generate password (first 4 letters of first name + @123)
            String password = PasswordUtil.generateDefaultPassword(firstName);
            String encryptedPassword = PasswordUtil.encryptPassword(password);
            
            String sql = "INSERT INTO User (UserName, Password, Role, CustomerCategory, Phone, EmailId, " +
                        "Address1, City, State, Country, ZipCode, DOB) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, firstName + " " + lastName);
            statement.setString(2, encryptedPassword);
            statement.setString(3, "Customer");
            statement.setString(4, ""); // Initially empty, will be updated based on usage
            statement.setLong(5, Long.parseLong(contactNumber));
            statement.setString(6, emailId);
            statement.setString(7, address);
            statement.setString(8, city);
            statement.setString(9, state);
            statement.setString(10, country);
            statement.setLong(11, Long.parseLong(zipCode));
            statement.setDate(12, Date.valueOf(dobStr));
            
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    String userId = String.valueOf(generatedKeys.getInt(1));
                    
                    // Show success message with user details
                    request.setAttribute("message", "Passenger Registration is successful.");
                    request.setAttribute("messageType", "success");
                    request.setAttribute("passengerId", userId);
                    request.setAttribute("generatedPassword", password);
                    request.getRequestDispatcher("registration-success.jsp").forward(request, response);
                    return;
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Issue in saving Customer Information. Please check the data and try again");
            request.setAttribute("messageType", "error");
            
        } finally {
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}