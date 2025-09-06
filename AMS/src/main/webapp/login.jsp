<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>AMS - Login</title>
<link rel="stylesheet" href="css/style.css">
<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="login-container">
		<div class="login-form">
			<h2>Airline Management System</h2>

			<% String error = (String) request.getAttribute("error"); 
               String success = (String) request.getAttribute("success"); %>

			<% if (error != null) { %>
			<div class="error-message">
				<%= error %>
			</div>
			<% } %>

			<% if (success != null) { %>
			<div class="success-message">
				<%= success %>
			</div>
			<% } %>

			<form action="LoginServlet" method="post"
				onsubmit="return validateLogin()">
				<div class="input-box">
					<input type="text" id="userId" name="userId" placeholder="User ID"
						data-tooltip="Enter your 5-digit numeric User ID" maxlength="5"
						required> <i class='bx bxs-user'></i>
					<div id="userId-error" class="field-error" style="display: none;"></div>
				</div>

				<div class="input-box">
					<input type="password" id="password" name="password"
						placeholder="Password"
						data-tooltip="Enter your password (6-30 characters)"
						maxlength="30" required> <i class='bx bxs-lock'></i>
					<div id="password-error" class="field-error" style="display: none;"></div>
				</div>

				<button type="submit" class="login-btn">Login</button>
			</form>

			<div class="register-link">
				<p>
					New User? <a href="register.jsp">Register Yourself</a>
				</p>
			</div>
		</div>
	</div>

	<script src="js/main.js"></script>
</body>
</html>