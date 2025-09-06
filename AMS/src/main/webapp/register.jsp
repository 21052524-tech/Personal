<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AMS - Registration</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="register-container">
        <div class="register-form">
            <h2>Passenger Registration</h2>
            
            <% String error = (String) request.getAttribute("error"); %>
            <% if (error != null) { %>
                <div class="error-message">
                    <%= error %>
                </div>
            <% } %>
            
            <form action="RegisterServlet" method="post" id="registerForm" data-autosave="true" 
                  onsubmit="return validateRegistration()">
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="firstName" class="required">First Name</label>
                        <input type="text" id="firstName" name="firstName" maxlength="50" 
                               data-tooltip="Enter your first name (max 50 characters)" required>
                        <div id="firstName-error" class="field-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="lastName" class="required">Last Name</label>
                        <input type="text" id="lastName" name="lastName" maxlength="50" 
                               data-tooltip="Enter your last name (max 50 characters)" required>
                        <div id="lastName-error" class="field-error"></div>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="dob" class="required">Date of Birth</label>
                        <input type="date" id="dob" name="dob" 
                               data-tooltip="Select your date of birth (must be after 1/1/1924)" required>
                        <div id="dob-error" class="field-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="emailId" class="required">Email ID</label>
                        <input type="email" id="emailId" name="emailId" maxlength="100" 
                               data-tooltip="Enter a valid email address" required>
                        <div id="emailId-error" class="field-error"></div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="address1" class="required">Address</label>
                    <textarea id="address1" name="address1" rows="3" maxlength="100" 
                              data-tooltip="Enter your street address (max 100 characters)" 
                              placeholder="Street, City, State details" required></textarea>
                    <div id="address1-error" class="field-error"></div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="phone" class="required">Contact Number</label>
                        <input type="tel" id="phone" name="phone" maxlength="10" 
                               data-tooltip="Enter your 10-digit mobile number" 
                               pattern="[0-9]{10}" required>
                        <div id="phone-error" class="field-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="address2">Address Line 2</label>
                        <input type="text" id="address2" name="address2" maxlength="100" 
                               data-tooltip="Additional address details (optional)">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="city" class="required">City</label>
                        <input type="text" id="city" name="city" maxlength="50" 
                               data-tooltip="Enter your city name" required>
                        <div id="city-error" class="field-error"></div>
                        <div id="city-error" class="field-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="state" class="required">State</label>
                        <input type="text" id="state" name="state" maxlength="50" 
                               data-tooltip="Enter your state name" required>
                        <div id="state-error" class="field-error"></div>
                        <div id="state-error" class="field-error"></div>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="country" class="required">Country</label>
                        <input type="text" id="country" name="country" maxlength="50" 
                               data-tooltip="Enter your country name" value="India" required>
                        <div id="country-error" class="field-error"></div>
                        <div id="country-error" class="field-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="zipCode" class="required">ZIP Code</label>
                        <input type="number" id="zipCode" name="zipCode" maxlength="10" 
                               data-tooltip="Enter your postal/ZIP code" required>
                        <div id="zipCode-error" class="field-error"></div>
                        <div id="zipCode-error" class="field-error"></div>
                    </div>
                </div>
                
                <div style="margin-top: 30px;">
                    <button type="submit" class="register-btn">Register</button>
                    <button type="reset" class="reset-btn" onclick="return confirmReset()">Reset</button>
                </div>
            </form>
            
            <div class="register-link" style="margin-top: 20px;">
                <p>Already have an account? <a href="login.jsp">Login Here</a></p>
            </div>
        </div>
    </div>
    
    <script src="js/main.js"></script>
</body>
</html>