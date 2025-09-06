<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ams.model.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"Admin".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Carrier - AMS</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@ include file="includes/header.jsp"%>

	<div class="main-content">
		<div class="admin-panel">
			<h2>Add Carrier Information</h2>

			<% String error = (String) request.getAttribute("error"); %>
			<% if (error != null) { %>
			<div class="error-message">
				<%= error %>
			</div>
			<% } %>

			<form action="AddCarrierServlet" method="post" id="carrierForm"
				data-autosave="true">
				<div class="form-row">
					<div class="form-group">
						<label for="carrierName" class="required">Carrier Name</label> <input
							type="text" id="carrierName" name="carrierName" maxlength="100"
							required data-tooltip="Enter the airline carrier name">
					</div>

					<div class="form-group">
						<label for="discount30Days" class="required">30 Days
							Advance Booking Discount (%)</label> <input type="number"
							id="discount30Days" name="discount30Days" min="0" max="100"
							required
							data-tooltip="Discount percentage for bookings 30 days in advance">
					</div>
				</div>

				<div class="form-row">
					<div class="form-group">
						<label for="discount60Days" class="required">60 Days
							Advance Booking Discount (%)</label> <input type="number"
							id="discount60Days" name="discount60Days" min="0" max="100"
							required
							data-tooltip="Discount percentage for bookings 60 days in advance">
					</div>

					<div class="form-group">
						<label for="discount90Days" class="required">90 Days
							Advance Booking Discount (%)</label> <input type="number"
							id="discount90Days" name="discount90Days" min="0" max="100"
							required
							data-tooltip="Discount percentage for bookings 90 days in advance">
					</div>
				</div>

				<div class="form-row">
					<div class="form-group">
						<label for="bulkDiscount" class="required">Bulk Booking
							Discount (%)</label> <input type="number" id="bulkDiscount"
							name="bulkDiscount" min="0" max="100" required
							data-tooltip="Discount percentage for bulk bookings">
					</div>

					<div class="form-group">
						<label for="silverDiscount" class="required">Silver User
							Discount (%)</label> <input type="number" id="silverDiscount"
							name="silverDiscount" min="0" max="100" required
							data-tooltip="Discount percentage for silver category users">
					</div>
				</div>

				<div class="form-row">
					<div class="form-group">
						<label for="goldDiscount" class="required">Gold User
							Discount (%)</label> <input type="number" id="goldDiscount"
							name="goldDiscount" min="0" max="100" required
							data-tooltip="Discount percentage for gold category users">
					</div>

					<div class="form-group">
						<label for="platinumDiscount" class="required">Platinum
							User Discount (%)</label> <input type="number" id="platinumDiscount"
							name="platinumDiscount" min="0" max="100" required
							data-tooltip="Discount percentage for platinum category users">
					</div>
				</div>

				<h3 style="color: #008B8B; margin: 30px 0 20px 0;">Refund
					Policies</h3>

				<div class="form-row">
					<div class="form-group">
						<label for="refund2Days" class="required">Refund % (2 Days
							Before Travel)</label> <input type="number" id="refund2Days"
							name="refund2Days" min="0" max="100" required
							data-tooltip="Refund percentage for cancellation 2 days before travel">
					</div>

					<div class="form-group">
						<label for="refund10Days" class="required">Refund % (10
							Days Before Travel)</label> <input type="number" id="refund10Days"
							name="refund10Days" min="0" max="100" required
							data-tooltip="Refund percentage for cancellation 10 days before travel">
					</div>
				</div>

				<div class="form-row">
					<div class="form-group">
						<label for="refund20Days" class="required">Refund % (20+
							Days Before Travel)</label> <input type="number" id="refund20Days"
							name="refund20Days" min="0" max="100" required
							data-tooltip="Refund percentage for cancellation 20+ days before travel">
					</div>

					<div class="form-group">
						<!-- Empty space for alignment -->
					</div>
				</div>

				<div style="margin-top: 30px;">
					<button type="submit" class="btn btn-success">Save Carrier</button>
					<a href="manageCarriers.jsp" class="btn btn-danger">Cancel</a>
				</div>
			</form>
		</div>
	</div>

	<%@ include file="includes/footer.jsp"%>
	<script src="js/main.js"></script>
</body>
</html>