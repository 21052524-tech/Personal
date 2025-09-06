<%@ page import="com.ams.model.User"%>
<%
    User headerUser = (User) session.getAttribute("user");
    if (headerUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<header class="header">
	<div class="nav-container">
		<div class="logo">AMS</div>

		<nav>
			<ul class="nav-menu">
				<li><a href="home.jsp">Home</a></li>
				<% if ("Customer".equals(headerUser.getRole())) { %>
				<li><a href="profile.jsp">My Profile</a></li>
				<li><a href="trips.jsp">My Trips</a></li>
				<% } else { %>
				<li><a href="manageCarriers.jsp">Manage Carriers</a></li>
				<li><a href="manageFlights.jsp">Manage Flights</a></li>
				<li><a href="viewBookings.jsp">View Bookings</a></li>
				<% } %>
				<li><a href="#" onclick="logout()">Logout</a></li>
			</ul>
		</nav>

		<div class="user-info">
			Welcome
			<%= headerUser.getUserName() %>!
		</div>
	</div>
</header>