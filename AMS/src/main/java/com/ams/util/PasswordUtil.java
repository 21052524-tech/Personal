package com.ams.util;

public class PasswordUtil {
    
    private static final int SHIFT = 3; // Caesar cipher shift
    
    /**
     * Encrypt password using Caesar cipher
     */
    public static String encryptPassword(String password) {
        if (password == null || password.isEmpty()) {
            return password;
        }
        
        StringBuilder encrypted = new StringBuilder();
        
        for (char character : password.toCharArray()) {
            if (Character.isLetter(character)) {
                // Encrypt only letters
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                character = (char) ((character - base + SHIFT) % 26 + base);
            }
            // Numbers and special characters remain unchanged
            encrypted.append(character);
        }
        
        return encrypted.toString();
    }
    
    /**
     * Decrypt password using Caesar cipher
     */
    public static String decryptPassword(String encryptedPassword) {
        if (encryptedPassword == null || encryptedPassword.isEmpty()) {
            return encryptedPassword;
        }
        
        StringBuilder decrypted = new StringBuilder();
        
        for (char character : encryptedPassword.toCharArray()) {
            if (Character.isLetter(character)) {
                // Decrypt only letters
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                character = (char) ((character - base - SHIFT + 26) % 26 + base);
            }
            // Numbers and special characters remain unchanged
            decrypted.append(character);
        }
        
        return decrypted.toString();
    }
    
    /**
     * Generate default password for registration
     * First four letters of first name + @123
     */
    public static String generateDefaultPassword(String firstName) {
        if (firstName == null || firstName.length() < 4) {
            return firstName + "@123";
        }
        return firstName.substring(0, 4) + "@123";
    }
    
    /**
     * Validate password strength
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c) && c != ' ') {
                hasSpecialChar = true;
            }
            
            // Check for space (not allowed)
            if (c == ' ') {
                return false;
            }
        }
        
        return hasUppercase && hasDigit && hasSpecialChar;
    }
    
    /**
     * Verify entered password with stored encrypted password
     */
    public static boolean verifyPassword(String enteredPassword, String storedEncryptedPassword) {
        if (enteredPassword == null || storedEncryptedPassword == null) {
            return false;
        }
        // Decrypt stored password and compare
        String decrypted = decryptPassword(storedEncryptedPassword);
        return enteredPassword.equals(decrypted);
    }
}
