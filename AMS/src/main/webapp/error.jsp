<%@ page contentType="text/html;charset=UTF-8" language="java"
	isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Error - AMS</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="login-container">
		<div class="login-form" style="text-align: center;">
			<h2 style="color: #dc3545; margin-bottom: 30px;">Oops! Something
				went wrong</h2>

			<div style="font-size: 4rem; color: #008B8B; margin-bottom: 20px;">
				✈️</div>

			<% 
                Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
                String errorMessage = (String) request.getAttribute("javax.servlet.error.message");
                
                if (statusCode != null) {
                    if (statusCode == 404) {
            %>
			<h3 style="color: #dc3545;">Page Not Found (404)</h3>
			<p style="color: rgba(255, 255, 255, 0.8); margin: 20px 0;">The
				page you're looking for doesn't exist or has been moved.</p>
			<%      } else if (statusCode == 500) { %>
			<h3 style="color: #dc3545;">Internal Server Error (500)</h3>
			<p style="color: rgba(255, 255, 255, 0.8); margin: 20px 0;">
				We're experiencing some technical difficulties. Please try again
				later.</p>
			<%      } else { %>
			<h3 style="color: #dc3545;">
				Error
				<%= statusCode %></h3>
			<p style="color: rgba(255, 255, 255, 0.8); margin: 20px 0;">An
				unexpected error occurred. Please contact support if the problem
				persists.</p>
			<%      }
                } else { %>
			<h3 style="color: #dc3545;">Unexpected Error</h3>
			<p style="color: rgba(255, 255, 255, 0.8); margin: 20px 0;">
				Something went wrong. Please try again or contact support.</p>
			<% } %>

			<div style="margin-top: 30px;">
				<a href="javascript:history.back()" class="login-btn"
					style="margin-right: 10px; display: inline-block; text-decoration: none;">
					Go Back </a> <a href="home.jsp" class="login-btn"
					style="background: rgba(255, 255, 255, 0.2); display: inline-block; text-decoration: none;">
					Home Page </a>
			</div>

			<div
				style="margin-top: 30px; color: rgba(255, 255, 255, 0.6); font-size: 0.9rem;">
				<p>If you continue to experience issues, please contact our
					support team at:</p>
				<p>Email: support@ams.com | Phone: 1800-123-4567</p>
			</div>
		</div>
	</div>

	<script>
        // Auto-redirect to home page after 15 seconds
        setTimeout(function() {
            window.location.href = 'home.jsp';
        }, 15000);
    </script>
</body>
</html>