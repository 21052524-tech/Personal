<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ams.model.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>AMS - Home</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@ include file="includes/header.jsp"%>

	<div class="main-content">
		<div class="hero-section">
			<h1>Welcome to Airline Management System</h1>
			<p>Book your flights with ease and comfort</p>
		</div>

		<% if ("Admin".equals(user.getRole())) { %>
		<!-- Admin Dashboard -->
		<div class="admin-panel">
			<h2>Admin Dashboard</h2>
			<div class="action-buttons">
				<a href="manageCarriers.jsp" class="btn btn-primary">Manage
					Carriers</a> <a href="manageFlights.jsp" class="btn btn-primary">Manage
					Flights</a> <a href="viewBookings.jsp" class="btn btn-primary">View
					All Bookings</a> <a href="reports.jsp" class="btn btn-warning">Reports</a>
			</div>

			<div class="card">
				<h3>Quick Stats</h3>
				<div class="stats-grid">
					<div class="stat-item">
						<h4>Total Carriers</h4>
						<span class="stat-number">5</span>
					</div>
					<div class="stat-item">
						<h4>Total Flights</h4>
						<span class="stat-number">25</span>
					</div>
					<div class="stat-item">
						<h4>Active Bookings</h4>
						<span class="stat-number">156</span>
					</div>
					<div class="stat-item">
						<h4>Total Users</h4>
						<span class="stat-number">892</span>
					</div>
				</div>
			</div>
		</div>
		<% } else { %>
		<!-- Customer Dashboard -->
		<div class="search-form">
			<h3 style="margin-bottom: 20px; color: #008B8B;">Search Flights</h3>
			<form action="SearchFlightServlet" method="get"
				onsubmit="return searchFlights()">
				<div class="search-row">
					<div class="form-group">
						<label for="origin">From</label> <select id="origin" name="origin"
							required data-tooltip="Select departure city">
							<option value="">Select Origin</option>
							<option value="Delhi">Delhi</option>
							<option value="Mumbai">Mumbai</option>
							<option value="Bangalore">Bangalore</option>
							<option value="Chennai">Chennai</option>
							<option value="Kolkata">Kolkata</option>
							<option value="Hyderabad">Hyderabad</option>
							<option value="Pune">Pune</option>
							<option value="Ahmedabad">Ahmedabad</option>
							<option value="Jaipur">Jaipur</option>
							<option value="Lucknow">Lucknow</option>
						</select>
					</div>

					<div class="form-group">
						<label for="destination">To</label> <select id="destination"
							name="destination" required
							data-tooltip="Select destination city">
							<option value="">Select Destination</option>
							<option value="Delhi">Delhi</option>
							<option value="Mumbai">Mumbai</option>
							<option value="Bangalore">Bangalore</option>
							<option value="Chennai">Chennai</option>
							<option value="Kolkata">Kolkata</option>
							<option value="Hyderabad">Hyderabad</option>
							<option value="Pune">Pune</option>
							<option value="Ahmedabad">Ahmedabad</option>
							<option value="Jaipur">Jaipur</option>
							<option value="Lucknow">Lucknow</option>
						</select>
					</div>

					<div class="form-group">
						<label for="departureDate">Departure Date</label> <input
							type="date" id="departureDate" name="departureDate" required
							data-tooltip="Select your travel date"
							min="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
					</div>

					<div class="form-group">
						<label for="travelers">Travelers</label> <input type="number"
							id="travelers" name="travelers" min="1" max="9" value="1"
							required data-tooltip="Number of passengers (1-9)">
					</div>

					<div class="form-group">
						<label for="flightClass">Class</label> <select id="flightClass"
							name="flightClass" required data-tooltip="Select travel class">
							<option value="Economy">Economy</option>
							<option value="Business">Business</option>
							<option value="Executive">Executive</option>
						</select>
					</div>
				</div>

				<button type="submit" class="search-btn">Search Flights</button>
			</form>
		</div>

		<!-- Popular Destinations -->
		<div class="card">
			<h3>Popular Destinations</h3>
			<div class="destinations-grid"
				style="display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 20px; margin-top: 20px;">
				<div class="destination-card"
					style="background: #f8f9fa; padding: 20px; border-radius: 10px; text-align: center;">
					<h4>Delhi</h4>
					<p>Capital of India</p>
					<span class="price">From ₹5,500</span>
				</div>
				<div class="destination-card"
					style="background: #f8f9fa; padding: 20px; border-radius: 10px; text-align: center;">
					<h4>Mumbai</h4>
					<p>Commercial Capital</p>
					<span class="price">From ₹6,200</span>
				</div>
				<div class="destination-card"
					style="background: #f8f9fa; padding: 20px; border-radius: 10px; text-align: center;">
					<h4>Bangalore</h4>
					<p>Silicon Valley of India</p>
					<span class="price">From ₹4,800</span>
				</div>
				<div class="destination-card"
					style="background: #f8f9fa; padding: 20px; border-radius: 10px; text-align: center;">
					<h4>Chennai</h4>
					<p>Detroit of India</p>
					<span class="price">From ₹5,100</span>
				</div>
			</div>
		</div>
		<% } %>
	</div>

	<%@ include file="includes/footer.jsp"%>
	<script src="js/main.js"></script>
</body>
</html>