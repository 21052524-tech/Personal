package com.ams.service;

import com.ams.dao.UserDAO;
import com.ams.model.User;

public class AuthService {
    
    private UserDAO userDAO = new UserDAO();
    
    public User validateUser(int userId, String password) {
        return userDAO.validateUser(userId, password);
    }
    
    public boolean registerUser(User user) {
        return userDAO.registerUser(user);
    }
    
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }
    
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }
    
    /**
     * Encrypts password using Caesar cipher with shift of 3
     */
    public String encryptPassword(String password) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encrypted.append((char) ((c - base + 3) % 26 + base));
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }
    
    /**
     * Decrypts password using Caesar cipher with shift of 3
     */
    public String decryptPassword(String encryptedPassword) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : encryptedPassword.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                decrypted.append((char) ((c - base - 3 + 26) % 26 + base));
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }
}