<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Success - AMS</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="register-container">
		<div class="register-form">
			<div class="success-message"
				style="margin-bottom: 30px; font-size: 1.5rem; background: #28a745;">
				Passenger Registration is successful.</div>

			<div class="card"
				style="background: #f8f9fa; border-radius: 10px; padding: 25px; margin-bottom: 25px;">
				<h3 style="color: #008B8B; margin-bottom: 20px;">Registration
					Details</h3>

				<div class="info-item" style="margin-bottom: 15px;">
					<label><strong>Passenger ID:</strong></label> <span
						style="font-size: 1.2rem; color: #007bff; font-weight: bold;"><%= request.getAttribute("userId") %></span>
				</div>

				<div class="info-item" style="margin-bottom: 15px;">
					<label><strong>Passenger Name:</strong></label> <span><%= request.getAttribute("userName") %></span>
				</div>

				<div class="info-item">
					<label><strong>Generated Password:</strong></label> <span
						style="font-family: monospace; background: #e9ecef; padding: 5px 10px; border-radius: 5px; color: #dc3545; font-weight: bold;"><%= request.getAttribute("password") %></span>
				</div>
			</div>

			<div
				style="background: #fff3cd; border: 1px solid #ffeaa7; border-radius: 8px; padding: 15px; margin-bottom: 25px;">
				<p style="margin: 0; color: #856404;">
					<strong>Important:</strong> Please note down your Passenger ID and
					Password for future login.
				</p>
			</div>

			<div style="text-align: center;">
				<a href="login.jsp" class="register-btn"
					style="text-decoration: none;"> Proceed to Login </a>
			</div>
		</div>
	</div>

	<script>
        // Auto-redirect to login after 10 seconds
        setTimeout(function() {
            window.location.href = 'login.jsp';
        }, 10000);
        
        // Show countdown
        let countdown = 10;
        const countdownElement = document.createElement('p');
        countdownElement.style.textAlign = 'center';
        countdownElement.style.color = '#666';
        countdownElement.style.marginTop = '20px';
        document.querySelector('.register-form').appendChild(countdownElement);
        
        function updateCountdown() {
            countdownElement.textContent = `Automatically redirecting to login page in ${countdown} seconds...`;
            countdown--;
            if (countdown >= 0) {
                setTimeout(updateCountdown, 1000);
            }
        }
        updateCountdown();
    </script>
</body>
</html>