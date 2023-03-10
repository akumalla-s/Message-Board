<%@page import="java.util.ArrayList"%>
<%@page import="neu.edu.data.UserRegistration"%>
<%@page import="neu.edu.data.UserSession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message Board</title>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/viewusers.css">
</head>
<body>

	<ul class="dashboard">
		<li class="logo"><img src="images/logo.png" alt="Logo" width="80"
			height="80" /></li>
		<li class="websitename">Message Board</li>

		<li><a href="logout">Logout</a></li>
		<li><a href="controller?action=adminDashboard">Admin Dashboard</a></li>
		

	</ul>
	
	<!-- Add your table of data below -->
	<div style="padding: 50px">
		<h1>User Data</h1>
		<table>
			<tr>
				<th>Username</th>
				<th>Email</th>
				<th>Role</th>
				<th>Action</th>

			</tr>
			<%
			ArrayList<UserRegistration> userRegistrations = (ArrayList<UserRegistration>) request.getAttribute("userRegistrations");
			for (UserRegistration userRegistration : userRegistrations) {
			%>
			<!-- Add more rows of data as needed -->
			<form action="controller" method="post">
				<tr>
					<td><%=userRegistration.getUsername()%></td>
					<td><%=userRegistration.getEmail()%></td>
					<td><%=userRegistration.getRole()%></td>
					<input type="hidden" name="username" value=<%=userRegistration.getUsername() %>>
					<input type="hidden" name="action" value="deleteUser"/>
					<td><input type="submit" value="Delete User"/></td>
				</tr>
				
			</form>

			<%
			}
			%>
		</table>
	</div>
	
	<script type="text/javascript">
	 function viewRow(button) {
	        var row = button.parentNode.parentNode;
	        var cells = row.getElementsByTagName("td");
	       	console.log(cells[0].innerHTML,cells[1].innerHTML,cells[2].innerHTML);
	 }
	
	</script>

	
</body>
</html>