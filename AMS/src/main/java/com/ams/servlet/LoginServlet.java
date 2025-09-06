package com.ams.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ams.util.DatabaseConnection;
import com.ams.util.PasswordUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Static user credentials for testing (as per requirements)
    private static final String[][] STATIC_USERS = {
        {"12345", "admin123", "Admin", "System Admin"},
        {"11111", "customer1", "Customer", "John Doe"},
        {"22222", "customer2", "Customer", "Jane Smith"}
    };
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        
        // Validate input
        if (userId == null || userId.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            request.setAttribute("message", "User ID and Password are required");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        // Validate userId format (numeric, max 5 digits)
        if (!userId.matches("\\d{1,5}")) {
            request.setAttribute("message", "ID not valid");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        // Validate password length
        if (password.length() < 6 || password.length() > 30) {
            request.setAttribute("message", "Password not valid");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        // Check static users first (for demo purposes)
        for (String[] user : STATIC_USERS) {
            if (user[0].equals(userId) && user[1].equals(password)) {
                // Login successful
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("userRole", user[2]);
                session.setAttribute("userName", user[3]);
                session.setMaxInactiveInterval(180); // 3 minutes
                
                response.sendRedirect("dashboard.jsp");
                return;
            }
        }
        
        // Check database users
        if (authenticateUser(userId, password, request, response)) {
            return;
        }
        
        // Check if it's ID or password or both that are wrong
        boolean userExists = checkUserExists(userId);
        if (userExists) {
            request.setAttribute("message", "Password not valid");
        } else {
            request.setAttribute("message", "ID not valid");
        }
        
        request.setAttribute("messageType", "error");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    private boolean authenticateUser(String userId, String password, 
                                   HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT UserID, UserName, Password, Role FROM User WHERE UserID = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("Password");
                
                if (PasswordUtil.verifyPassword(password, storedPassword)) {
                    // Login successful
                    HttpSession session = request.getSession();
                    session.setAttribute("userId", userId);
                    session.setAttribute("userRole", resultSet.getString("Role"));
                    session.setAttribute("userName", resultSet.getString("UserName"));
                    session.setMaxInactiveInterval(180); // 3 minutes
                    
                    response.sendRedirect("dashboard.jsp");
                    return true;
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Database error occurred");
            request.setAttribute("messageType", "error");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return false;
    }
    
    private boolean checkUserExists(String userId) {
        // Check static users
        for (String[] user : STATIC_USERS) {
            if (user[0].equals(userId)) {
                return true;
            }
        }
        
        // Check database
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT UserID FROM User WHERE UserID = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            
            resultSet = statement.executeQuery();
            return resultSet.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return false;
    }
}
