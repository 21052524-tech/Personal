<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ams.model.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    if (!"Customer".equals(user.getRole())) {
        response.sendRedirect("home.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Profile - AMS</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@ include file="includes/header.jsp"%>

	<div class="main-content">
		<div class="profile-section">
			<div
				style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px;">
				<h2 style="color: #008B8B;">My Profile</h2>
				<button id="editBtn" class="btn btn-primary" onclick="toggleEdit()">Edit
					Profile</button>
			</div>

			<% String success = (String) request.getAttribute("success");
               String error = (String) request.getAttribute("error"); %>

			<% if (success != null) { %>
			<div class="success-message">
				<%= success %>
			</div>
			<% } %>

			<% if (error != null) { %>
			<div class="error-message">
				<%= error %>
			</div>
			<% } %>

			<form id="profileForm" action="UpdateProfileServlet" method="post">
				<div class="profile-info">
					<div class="info-item">
						<label>User ID</label> <span><%= user.getUserId() %></span>
					</div>

					<div class="info-item">
						<label>Full Name</label> <input type="text" name="userName"
							value="<%= user.getUserName() != null ? user.getUserName() : "" %>"
							readonly>
					</div>

					<div class="info-item">
						<label>Role</label> <span><%= user.getRole() %></span>
					</div>

					<div class="info-item">
						<label>Customer Category</label> <span><%= user.getCustomerCategory() != null ? user.getCustomerCategory() : "Not Assigned" %></span>
					</div>

					<div class="info-item">
						<label>Phone Number</label> <input type="tel" name="phone"
							value="<%= user.getPhone() %>" readonly>
					</div>

					<div class="info-item">
						<label>Email ID</label> <input type="email" name="emailId"
							value="<%= user.getEmailId() != null ? user.getEmailId() : "" %>"
							readonly>
					</div>

					<div class="info-item">
						<label>Address 1</label> <input type="text" name="address1"
							value="<%= user.getAddress1() != null ? user.getAddress1() : "" %>"
							readonly>
					</div>

					<div class="info-item">
						<label>Address 2</label> <input type="text" name="address2"
							value="<%= user.getAddress2() != null ? user.getAddress2() : "" %>"
							readonly>
					</div>

					<div class="info-item">
						<label>City</label> <input type="text" name="city"
							value="<%= user.getCity() != null ? user.getCity() : "" %>"
							readonly>
					</div>

					<div class="info-item">
						<label>State</label> <input type="text" name="state"
							value="<%= user.getState() != null ? user.getState() : "" %>"
							readonly>
					</div>

					<div class="info-item">
						<label>Country</label> <input type="text" name="country"
							value="<%= user.getCountry() != null ? user.getCountry() : "" %>"
							readonly>
					</div>

					<div class="info-item">
						<label>ZIP Code</label> <input type="text" name="zipCode"
							value="<%= user.getZipCode() %>" readonly>
					</div>

					<div class="info-item">
						<label>Date of Birth</label> <input type="date" name="dob"
							value="<%= user.getDob() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(user.getDob()) : "" %>"
							readonly>
					</div>
				</div>

				<div id="formActions"
					style="margin-top: 30px; text-align: center; display: none;">
					<button type="submit" class="btn btn-success">Save Changes</button>
					<button type="button" class="btn btn-danger" onclick="cancelEdit()">Cancel</button>
				</div>
			</form>
		</div>
	</div>

	<%@ include file="includes/footer.jsp"%>

	<script>
        let isEditing = false;
        
        function toggleEdit() {
            const inputs = document.querySelectorAll('#profileForm input[type="text"], #profileForm input[type="email"], #profileForm input[type="tel"], #profileForm input[type="date"]');
            const editBtn = document.getElementById('editBtn');
            const formActions = document.getElementById('formActions');
            
            if (!isEditing) {
                // Enable editing
                inputs.forEach(input => {
                    input.removeAttribute('readonly');
                    input.style.background = 'white';
                    input.style.border = '1px solid #ddd';
                });
                editBtn.style.display = 'none';
                formActions.style.display = 'block';
                isEditing = true;
            }
        }
        
        function cancelEdit() {
            const inputs = document.querySelectorAll('#profileForm input[type="text"], #profileForm input[type="email"], #profileForm input[type="tel"], #profileForm input[type="date"]');
            const editBtn = document.getElementById('editBtn');
            const formActions = document.getElementById('formActions');
            
            // Disable editing
            inputs.forEach(input => {
                input.setAttribute('readonly', true);
                input.style.background = 'transparent';
                input.style.border = 'none';
            });
            editBtn.style.display = 'block';
            formActions.style.display = 'none';
            isEditing = false;
            
            // Reset form
            document.getElementById('profileForm').reset();
            location.reload();
        }
    </script>

	<script src="js/main.js"></script>
</body>
</html>