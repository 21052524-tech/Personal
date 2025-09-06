package com.ams.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public static void main(String[] args) {
	    if (testConnection()) {
	        System.out.println("✅ Database connected successfully!");
	    } else {
	        System.out.println("❌ Failed to connect. Check username, password, DB name, or MySQL server.");
	    }
	}

    
    private static final String URL = "jdbc:mysql://localhost:3306/ams_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Rudra1@mysql2";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}